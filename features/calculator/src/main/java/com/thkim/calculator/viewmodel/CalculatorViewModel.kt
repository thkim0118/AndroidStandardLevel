package com.thkim.calculator.viewmodel

import androidx.lifecycle.ViewModel
import com.terry.repository.usecase.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-05
 */
@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {

    fun getHistoryAll() = getHistoryUseCase.getHistoryAll()

}