package com.terry.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.terry.local.dao.BookSearchHistoryDao
import com.terry.local.model.BookSearchHistory

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@Database(entities = [BookSearchHistory::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun bookSearchHistoryDao(): BookSearchHistoryDao
}