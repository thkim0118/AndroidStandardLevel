package com.terry.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.terry.local.model.History
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-07-04
 */
@Dao
interface HistoryDao {

    @Query("SELECT * FROM History")
    fun getAll(): Flow<List<History>>

    @Insert
    suspend fun insertHistory(history: History): Long

    @Query("DELETE FROM History")
    suspend fun deleteAll(): Int

    @Delete
    fun delete(history: History)

    @Query("SELECT * FROM History WHERE result LIKE :result LIMIT 1")
    fun findByResult(result: String): History
}