package com.terry.repository.source

import com.terry.local.model.History

/*
 * Created by Taehyung Kim on 2021-07-04
 */
interface HistoryDataSource {
    fun getHistoryAll(): List<History>
}