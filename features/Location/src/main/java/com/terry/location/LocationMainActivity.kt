package com.terry.location

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.terry.common.base.BaseActivity
import com.terry.common.di.UseCaseDependencies
import com.terry.location.MapActivity.Companion.SEARCH_RESULT_EXTRA_KEY
import com.terry.location.databinding.ActivityLocationMainBinding
import com.terry.location.di.DaggerLocationComponent
import com.terry.location.model.LocationLatLngEntity
import com.terry.location.model.SearchResultEntity
import com.terry.location.viewmodel.LocationViewModel
import com.terry.remote.model.location.Poi
import com.terry.remote.model.location.Pois
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class LocationMainActivity :
    BaseActivity<ActivityLocationMainBinding>(ActivityLocationMainBinding::inflate) {

    private lateinit var searchAdapter: SearchRecyclerAdapter

    @Inject
    lateinit var viewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependentInjection()
        super.onCreate(savedInstanceState)

        initAdapter()
        initViews()
        bindViews()
        initData()
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

    private fun initViews() = with(binding) {
        emptyResultTextView.isVisible = false
        recyclerView.adapter = searchAdapter
    }

    private fun bindViews() = with(binding) {
        searchButton.setOnClickListener {
            searchKeyword(searchBarInputView.text.toString())
        }
    }

    private fun initAdapter() {
        searchAdapter = SearchRecyclerAdapter()
    }

    private fun initData() {
        searchAdapter.notifyDataSetChanged()
    }

    private fun setData(pois: Pois) {
        val dataList = pois.poi.map {
            SearchResultEntity(
                name = it.name ?: "빌딩명 없음",
                fullAddress = makeMainAddress(it),
                locationLatLng = LocationLatLngEntity(
                    latitude = it.noorLat,
                    longitude = it.noorLon
                )
            )
        }
        searchAdapter.setSearchResultList(dataList) {
            Snackbar.make(
                binding.root,
                "빌딩 이름 : ${it.name}, 주소 : ${it.fullAddress}, 위도/경도 : ${it.locationLatLng}",
                Snackbar.LENGTH_SHORT
            ).show()
            startActivity(
                Intent(this, MapActivity::class.java).apply {
                    putExtra(SEARCH_RESULT_EXTRA_KEY, it)
                }
            )
        }
    }

    private fun observeLiveData() {
        viewModel.searchLocationData.observe(this) { searchResponse ->
            setData(searchResponse.searchPoiInfo.pois)
        }
    }

    private fun searchKeyword(keywordString: String) {
        viewModel.getSearchLocation(keywordString)
    }

    private fun makeMainAddress(poi: Poi): String =
        if (poi.secondNo?.trim().isNullOrEmpty()) {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    poi.firstNo?.trim()
        } else {
            (poi.upperAddrName?.trim() ?: "") + " " +
                    (poi.middleAddrName?.trim() ?: "") + " " +
                    (poi.lowerAddrName?.trim() ?: "") + " " +
                    (poi.detailAddrName?.trim() ?: "") + " " +
                    (poi.firstNo?.trim() ?: "") + " " +
                    poi.secondNo?.trim()
        }
}