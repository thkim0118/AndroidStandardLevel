package com.thkim.calculator

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thkim.calculator.dao.HistoryDao
import com.thkim.calculator.model.History

/*
 * Created by Taehyung Kim on 2021-06-28
 */
@Database(entities = [History::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}