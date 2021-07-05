package com.terry.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.terry.local.dao.HistoryDao
import com.terry.local.model.History

/*
 * Created by Taehyung Kim on 2021-07-04
 */
@Database(entities = [History::class], version = 1, exportSchema = true)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}