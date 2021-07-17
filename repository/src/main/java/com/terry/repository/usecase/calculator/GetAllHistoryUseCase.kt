package com.terry.repository.usecase.calculator

import com.terry.local.model.History
import com.terry.repository.repo.calculator.HistoryRepository
import com.terry.repository.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-04
 */
class GetAllHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
): BaseUseCase<Unit, Flow<List<History>>>() {

    override fun execute(parameter: Unit): Flow<List<History>> {
        return historyRepository.getHistoryAll()
    }

}