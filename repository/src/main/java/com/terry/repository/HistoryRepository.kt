package com.terry.repository

import com.terry.local.model.History

/*
 * Created by Taehyung Kim on 2021-07-04
 */
interface HistoryRepository {

    fun getHistoryAll(): List<History>

}