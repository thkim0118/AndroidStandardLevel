package com.terry.repository.repo.calculator

import com.terry.local.model.History
import com.terry.repository.source.calculator.HistoryDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-04
 */
class HistoryRepositoryImpl @Inject constructor(
    private val historyDataSource: HistoryDataSource
) : HistoryRepository {

    override fun getHistoryAll(): Flow<List<History>> {
        return historyDataSource.getHistoryAll()
    }

    override suspend fun insertHistory(history: History): Long {
        return historyDataSource.insertHistory(history)
    }

    override suspend fun deleteAll(): Int {
        return historyDataSource.deleteAll()
    }
}