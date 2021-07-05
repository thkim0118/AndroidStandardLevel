package com.terry.repository.source

import com.terry.local.dao.HistoryDao
import com.terry.local.model.History
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-05
 */
class HistoryDataSourceImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryDataSource {

    override fun getHistoryAll(): List<History> {
        return historyDao.getAll()
    }
}