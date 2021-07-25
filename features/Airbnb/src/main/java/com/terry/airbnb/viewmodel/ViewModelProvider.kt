package com.terry.airbnb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.repository.usecase.airbnb.GetHouseListUseCase
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-25
 */
class HouseMainViewModelProvider @Inject constructor(
    private val getHouseListUseCase: GetHouseListUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != HouseMainViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return HouseMainViewModel(
            getHouseListUseCase
        ) as T
    }

}