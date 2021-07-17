package com.terry.repository.source.calculator

import com.terry.local.model.History
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-07-04
 */
interface HistoryDataSource {
    fun getHistoryAll(): Flow<List<History>>

    suspend fun insertHistory(history: History): Long

    suspend fun deleteAll(): Int
}