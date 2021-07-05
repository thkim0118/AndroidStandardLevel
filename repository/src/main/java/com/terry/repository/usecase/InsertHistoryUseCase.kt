package com.terry.repository.usecase

import com.terry.local.model.History
import com.terry.repository.HistoryRepository
import com.terry.repository.base.CoroutineUseCase
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-05
 */
class InsertHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) : CoroutineUseCase<History, Long>() {

    override suspend fun execute(parameter: History): Long {
        return historyRepository.insertHistory(parameter)
    }
}