package com.terry.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.terry.local.model.BookSearchHistory
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@Dao
interface BookSearchHistoryDao {

    @Query("SELECT * FROM BookSearchHistory")
    fun getAll(): Flow<List<BookSearchHistory>>

    @Insert
    fun insertBookSearchHistory(bookSearchHistory: BookSearchHistory)

    @Query("DELETE FROM BookSearchHistory WHERE keyword == :keyword")
    fun delete(keyword: String)
}