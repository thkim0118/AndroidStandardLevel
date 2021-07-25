package com.terry.airbnb

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.terry.airbnb.databinding.ActivityAirbnbMainBinding
import com.terry.airbnb.di.DaggerHouseComponent
import com.terry.airbnb.viewmodel.HouseMainViewModel
import com.terry.common.base.BaseActivity
import com.terry.common.di.UseCaseDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class HouseMainActivity :
    BaseActivity<ActivityAirbnbMainBinding>(ActivityAirbnbMainBinding::inflate),
    OnMapReadyCallback,
    Overlay.OnClickListener {

    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val mapView by lazy {
        binding.mapView
    }

    private val viewPager by lazy { binding.houseViewPager }

    private val bottomRecyclerView by lazy { binding.bottomSheet.bottomModelRecyclerView }

    private val viewPagerAdapter = HouseViewPagerAdapter(itemClicked = {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "[지금 이 가격에 예약하세요 !!] ${it.title} ${it.price}  사진보기 : ${it.imgUrl}"
            )
            type = "text/plain"
        }

        startActivity(Intent.createChooser(intent, null))
    })

    private val bottomRecyclerAdapter = HouseListAdapter()

    @Inject
    lateinit var viewModel: HouseMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync(this)

        viewPager.adapter = viewPagerAdapter
        bottomRecyclerView.adapter = bottomRecyclerAdapter
        bottomRecyclerView.layoutManager = LinearLayoutManager(this)

        initPagerListener()
        observeLiveData()
    }

    override fun onMapReady(map: NaverMap) {
        naverMap = map

        naverMap.maxZoom = 18.0
        naverMap.minZoom = 10.0

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.573845085295574, 126.96218180833912))
        naverMap.moveCamera(cameraUpdate)

        val uiSetting = naverMap.uiSettings
        uiSetting.isLocationButtonEnabled = false

        binding.currentLocationButton.map = naverMap

        locationSource =
            FusedLocationSource(this@HouseMainActivity, LOCATION_PERMISSION_REQUEST_CODE)

        naverMap.locationSource = locationSource

        val marker = Marker()
        marker.position = LatLng(37.57516368258539, 126.962950679808)
        marker.map = naverMap
        marker.icon = MarkerIcons.RED

        viewModel.getHouseList()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }

        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    private fun initCoreDependentInjection() {
        val useCaseDependencies = EntryPointAccessors.fromApplication(
            applicationContext,
            UseCaseDependencies::class.java
        )

        DaggerHouseComponent.factory().create(
            dependencies = useCaseDependencies,
            activity = this
        ).inject(this)
    }

    private fun initPagerListener() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val selectedHouseModel = viewPagerAdapter.currentList[position]
                val cameraUpdate =
                    CameraUpdate
                        .scrollTo(LatLng(selectedHouseModel.lat, selectedHouseModel.lng))
                        .animate(CameraAnimation.Easing)

                naverMap.moveCamera(cameraUpdate)
            }
        })
    }

    private fun observeLiveData() {
        viewModel.houseList.observe(this) { houseList ->
            houseList.forEach { house ->
                val marker = Marker()
                marker.position = LatLng(house.lat, house.lng)
                marker.onClickListener = this
                marker.map = naverMap
                marker.tag = house.id
                marker.icon = MarkerIcons.BLACK
                marker.iconTintColor = Color.RED

                viewPagerAdapter.submitList(houseList)
                bottomRecyclerAdapter.submitList(houseList)
                binding.bottomSheet.bottomSheetTitleTextView.text = "${houseList.size}개의 숙소"
            }
        }
    }

    override fun onClick(overlay: Overlay): Boolean {
        overlay.tag

        val selectedModel = viewPagerAdapter.currentList.firstOrNull {
            it.id == overlay.tag
        }

        selectedModel?.let {
            val position = viewPagerAdapter.currentList.indexOf(it)
            viewPager.currentItem = position
        }

        return true
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 10001
    }
}