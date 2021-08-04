package com.terry.location.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terry.common.LogT
import com.terry.remote.model.location.SearchResponse
import com.terry.repository.usecase.location.GetReverseGeoCodeUseCase
import com.terry.repository.usecase.location.GetSearchLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-08-01
 */
@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getSearchLocationUseCase: GetSearchLocationUseCase
) : ViewModel() {

    private val _searchLocationData = MutableLiveData<SearchResponse>()
    val searchLocationData: LiveData<SearchResponse>
        get() = _searchLocationData

    fun getSearchLocation(keyword: String) = viewModelScope.launch {
        try {
            val response = getSearchLocationUseCase(keyword)

            if (response.isSuccessful) {
                response.body()?.let {
                    _searchLocationData.postValue(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LogT.e(e.stackTraceToString())
        }

    }

}