package com.terry.local.di

import android.content.Context
import androidx.room.Room
import com.terry.local.BooksDatabase
import com.terry.local.HistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
 * Created by Taehyung Kim on 2021-07-04
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext application: Context): HistoryDatabase {
        return Room.databaseBuilder(
            application,
            HistoryDatabase::class.java,
            "historyDB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideHistoryDao(historyDatabase: HistoryDatabase) = historyDatabase.historyDao()

    @Singleton
    @Provides
    fun provideBookSearchHistory(@ApplicationContext application: Context): BooksDatabase {
        return Room.databaseBuilder(
            application,
            BooksDatabase::class.java,
            "BookSearchHistoryDB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideBookSearchHistoryDao(booksDatabase: BooksDatabase) =
        booksDatabase.bookSearchHistoryDao()
}