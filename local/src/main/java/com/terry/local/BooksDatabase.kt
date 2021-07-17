package com.terry.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.terry.local.dao.BookSearchHistoryDao
import com.terry.local.dao.ReviewDao
import com.terry.local.model.BookSearchHistory
import com.terry.local.model.Review

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@Database(entities = [BookSearchHistory::class, Review::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun bookSearchHistoryDao(): BookSearchHistoryDao
    abstract fun reviewDao(): ReviewDao
}