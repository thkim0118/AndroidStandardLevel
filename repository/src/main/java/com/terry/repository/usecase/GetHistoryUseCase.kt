package com.terry.repository.usecase

import com.terry.local.model.History
import com.terry.repository.HistoryRepository
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-04
 */
class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {

    fun getHistoryAll(): List<History> {
        return historyRepository.getHistoryAll()
    }

}