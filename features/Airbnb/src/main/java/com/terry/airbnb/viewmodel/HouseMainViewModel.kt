package com.terry.airbnb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terry.common.LogT
import com.terry.remote.model.airbnb.HouseModel
import com.terry.repository.usecase.airbnb.GetHouseListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-25
 */
@HiltViewModel
class HouseMainViewModel @Inject constructor(
    private val getHouseListUseCase: GetHouseListUseCase
) : ViewModel() {

    private val _houseList = MutableLiveData<List<HouseModel>>()
    val houseList: LiveData<List<HouseModel>>
        get() = _houseList

    fun getHouseList() = viewModelScope.launch {
        val result = getHouseListUseCase()

        try {
            if (result.isSuccessful) {
                val response = result.body()

                response?.let { houseList ->
                    LogT.d(houseList.toString())
                    _houseList.postValue(houseList.items)
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            LogT.e(e.stackTraceToString())
        }
    }

}