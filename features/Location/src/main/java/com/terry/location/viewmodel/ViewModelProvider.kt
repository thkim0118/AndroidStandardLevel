package com.terry.location.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.repository.usecase.location.GetReverseGeoCodeUseCase
import com.terry.repository.usecase.location.GetSearchLocationUseCase
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-08-01
 */
class LocationViewModelProvider @Inject constructor(
    private val getSearchLocationUseCase: GetSearchLocationUseCase,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != LocationViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return LocationViewModel(
            getSearchLocationUseCase
        ) as T
    }
}

class MapViewModelProvider @Inject constructor(
    private val getReverseGeoCodeUseCase: GetReverseGeoCodeUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != MapViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return MapViewModel(
            getReverseGeoCodeUseCase
        ) as T
    }

}