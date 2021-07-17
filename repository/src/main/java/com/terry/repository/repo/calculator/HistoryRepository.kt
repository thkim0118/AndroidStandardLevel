package com.terry.repository.repo.calculator

import com.terry.local.model.History
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-07-04
 */
interface HistoryRepository {

    fun getHistoryAll(): Flow<List<History>>

    suspend fun insertHistory(history: History): Long

    suspend fun deleteAll(): Int
}