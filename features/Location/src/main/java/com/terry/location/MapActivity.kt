package com.terry.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.terry.common.base.BaseActivity
import com.terry.common.di.UseCaseDependencies
import com.terry.location.databinding.ActivityMapBinding
import com.terry.location.di.DaggerLocationComponent
import com.terry.location.model.LocationLatLngEntity
import com.terry.location.model.SearchResultEntity
import com.terry.location.viewmodel.MapViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-08-04
 */
class MapActivity : BaseActivity<ActivityMapBinding>(ActivityMapBinding::inflate),
    OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private var currentSelectedMarker: Marker? = null

    private lateinit var searchResult: SearchResultEntity

    private lateinit var locationManager: LocationManager

    private lateinit var myLocationListener: MyLocationListener

    private var myLocationLatLng: LocationLatLngEntity? = null

    @Inject
    lateinit var viewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependentInjection()
        super.onCreate(savedInstanceState)

        if (::searchResult.isInitialized.not()) {
            intent?.let {
                searchResult = it.getParcelableExtra(SEARCH_RESULT_EXTRA_KEY)
                    ?: throw Exception("데이터가 존재하지 않습니다.")
            }

            setupGoogleMap()
            bindViews()
        }

        observeLiveData()
    }

    private fun initDependentInjection() {
        val useCaseDependencies = EntryPointAccessors.fromApplication(
            applicationContext,
            UseCaseDependencies::class.java
        )

        DaggerLocationComponent.factory().create(
            dependentModule = useCaseDependencies,
            activity = this
        ).inject(this)
    }

    private fun bindViews() = with(binding) {
        currentLocationButton.setOnClickListener {
            getMyLocation()
        }
    }

    private fun observeLiveData() {
        viewModel.reverseGeoInfo.observe(this) {
            val myLocationLatLng = myLocationLatLng ?: return@observe
            currentSelectedMarker = setupMarker(
                SearchResultEntity(
                    fullAddress = it.addressInfo.fullAddress ?: "주소 정보 없음",
                    name = "나의 위치",
                    locationLatLng = myLocationLatLng
                )
            )

            currentSelectedMarker?.showInfoWindow()
        }
    }

    private fun setupGoogleMap() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        currentSelectedMarker = setupMarker(searchResult)

        currentSelectedMarker?.showInfoWindow()
    }

    private fun setupMarker(searchEntity: SearchResultEntity): Marker? {
        val positionLatLng = LatLng(
            searchEntity.locationLatLng.latitude.toDouble(),
            searchEntity.locationLatLng.longitude.toDouble()
        )
        val markerOptions = MarkerOptions().apply {
            position(positionLatLng)
            title(searchResult.name)
            snippet(searchResult.fullAddress)
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(positionLatLng, CAMERA_ZOOM_LEVEL))

        return map.addMarker(markerOptions)
    }

    private fun getMyLocation() {
        if (::locationManager.isInitialized.not()) {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }

        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsEnabled) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                setMyLocationListener()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setMyLocationListener() {
        val minTime = 1500L
        val minDistance = 100f

        if (::myLocationListener.isInitialized.not()) {
            myLocationListener = MyLocationListener()
        }

        with(locationManager) {
            requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime, minDistance, myLocationListener
            )
            requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTime, minDistance, myLocationListener
            )
        }
    }

    private fun onCurrentLocationChanged(locationLatLngEntity: LocationLatLngEntity) {
        myLocationLatLng = locationLatLngEntity

        map.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    locationLatLngEntity.latitude.toDouble(),
                    locationLatLngEntity.longitude.toDouble()
                ), CAMERA_ZOOM_LEVEL
            )
        )
        loadReverseGeoInformation(locationLatLngEntity)
        removeLocationListener()
    }

    private fun loadReverseGeoInformation(locationEntity: LocationLatLngEntity) {
        viewModel.getReverseGeoInformation(
            locationEntity.latitude.toDouble(),
            locationEntity.longitude.toDouble()
        )
    }

    private fun removeLocationListener() {
        if (::locationManager.isInitialized && ::myLocationListener.isInitialized) {
            locationManager.removeUpdates(myLocationListener)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                setMyLocationListener()
            } else {
                Snackbar.make(binding.root, "권한을 받지 못하였습니다.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    inner class MyLocationListener : LocationListener {

        override fun onLocationChanged(location: Location) {
            val locationLatLngEntity = LocationLatLngEntity(
                location.latitude.toFloat(),
                location.longitude.toFloat()
            )
            onCurrentLocationChanged(locationLatLngEntity)
        }

    }

    companion object {
        const val SEARCH_RESULT_EXTRA_KEY = "SEARCH_RESULT_EXTRA_KEY"
        const val CAMERA_ZOOM_LEVEL = 17f
        const val PERMISSION_REQUEST_CODE = 1001
    }
}