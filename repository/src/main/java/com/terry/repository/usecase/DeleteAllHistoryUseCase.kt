package com.terry.repository.usecase

import com.terry.repository.HistoryRepository
import com.terry.repository.base.CoroutineUseCase
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-05
 */
class DeleteAllHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) : CoroutineUseCase<Unit, Int>() {

    override suspend fun execute(parameter: Unit): Int {
        return historyRepository.deleteAll()
    }
}