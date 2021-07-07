package com.thkim.calculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.terry.common.LogT
import com.terry.local.model.History
import com.terry.repository.usecase.DeleteAllHistoryUseCase
import com.terry.repository.usecase.GetAllHistoryUseCase
import com.terry.repository.usecase.InsertHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-05
 */
@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val getAllHistoryUseCase: GetAllHistoryUseCase,
    private val insertHistoryUseCase: InsertHistoryUseCase,
    private val deleteAllHistoryUseCase: DeleteAllHistoryUseCase
) : ViewModel() {

    val getAllHistory = getAllHistoryUseCase(Unit).asLiveData()

    fun insertHistory(history: History) = viewModelScope.launch {
        LogT.start()
        insertHistoryUseCase(history)
    }

    fun deleteAllHistory() = viewModelScope.launch {
        deleteAllHistoryUseCase(Unit)
    }
}