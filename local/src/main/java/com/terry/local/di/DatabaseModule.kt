package com.terry.local.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `REVIEW` (`id` INTEGER, `review` TEXT," + "PRIMARY KEY(`id`))")
            }
        }

        return Room.databaseBuilder(
            application,
            BooksDatabase::class.java,
            "BookSearchHistoryDB"
        )
            .addMigrations(migration_1_2)
            .build()
    }

    @Singleton
    @Provides
    fun provideBookSearchHistoryDao(booksDatabase: BooksDatabase) =
        booksDatabase.bookSearchHistoryDao()

    @Singleton
    @Provides
    fun provideReviewDao(booksDatabase: BooksDatabase) =
        booksDatabase.reviewDao()
}