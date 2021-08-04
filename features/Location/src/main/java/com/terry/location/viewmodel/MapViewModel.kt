package com.terry.location.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terry.common.LogT
import com.terry.remote.model.location.address.AddressInfoResponse
import com.terry.repository.usecase.location.GetReverseGeoCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-08-04
 */
@HiltViewModel
class MapViewModel @Inject constructor(
    private val getReverseGeoCodeUseCase: GetReverseGeoCodeUseCase
) : ViewModel() {

    private val _reverseGeoInfo = MutableLiveData<AddressInfoResponse>()
    val reverseGeoInfo: LiveData<AddressInfoResponse>
        get() = _reverseGeoInfo

    fun getReverseGeoInformation(lat: Double, lon: Double) = viewModelScope.launch {
        try {
            val response = getReverseGeoCodeUseCase(lat, lon)

            if (response.isSuccessful) {
                response.body()?.let {
                    _reverseGeoInfo.postValue(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LogT.e(e.stackTraceToString())
        }
    }
}