package com.thkim.calculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.repository.usecase.GetHistoryUseCase
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-05
 */
class ViewModelFactory

class CalculatorViewModelFactory @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != CalculatorViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return CalculatorViewModel(
            getHistoryUseCase
        ) as T
    }

}