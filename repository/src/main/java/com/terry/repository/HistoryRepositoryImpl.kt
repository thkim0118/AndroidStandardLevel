package com.terry.repository

import com.terry.local.model.History
import com.terry.repository.source.HistoryDataSource
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-04
 */
class HistoryRepositoryImpl @Inject constructor(
    private val historyDataSource: HistoryDataSource
) : HistoryRepository {

    override fun getHistoryAll(): List<History> {
        return historyDataSource.getHistoryAll()
    }
}