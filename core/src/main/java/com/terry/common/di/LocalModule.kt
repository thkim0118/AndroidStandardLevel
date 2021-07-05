package com.terry.common.di

import com.terry.local.di.DatabaseModule
import com.terry.repository.HistoryRepository
import com.terry.repository.HistoryRepositoryImpl
import com.terry.repository.source.HistoryDataSource
import com.terry.repository.source.HistoryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
 * Created by Taehyung Kim on 2021-07-05
 */
@InstallIn(SingletonComponent::class)
@Module(includes = [DatabaseModule::class])
interface LocalModule {

    @Singleton
    @Binds
    fun bindLocalDataSource(historyDataSource: HistoryDataSourceImpl): HistoryDataSource

    @Singleton
    @Binds
    fun bindRepository(repository: HistoryRepositoryImpl): HistoryRepository
}