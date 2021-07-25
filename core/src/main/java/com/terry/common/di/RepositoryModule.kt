package com.terry.common.di

import com.terry.local.di.DatabaseModule
import com.terry.remote.di.NetworkModule
import com.terry.repository.repo.airbnb.HouseRepository
import com.terry.repository.repo.airbnb.HouseRepositoryImpl
import com.terry.repository.repo.books.local.BookSearchHistoryRepository
import com.terry.repository.repo.books.local.BookSearchHistoryRepositoryImpl
import com.terry.repository.repo.books.local.ReviewRepository
import com.terry.repository.repo.books.local.ReviewRepositoryImpl
import com.terry.repository.repo.books.remote.BookServiceRepository
import com.terry.repository.repo.books.remote.BookServiceRepositoryImpl
import com.terry.repository.repo.calculator.HistoryRepository
import com.terry.repository.repo.calculator.HistoryRepositoryImpl
import com.terry.repository.source.airbnb.HouseDataSource
import com.terry.repository.source.airbnb.HouseDataSourceImpl
import com.terry.repository.source.books.local.BookSearchHistoryDataSource
import com.terry.repository.source.books.local.BookSearchHistoryDataSourceImpl
import com.terry.repository.source.books.local.ReviewDataSource
import com.terry.repository.source.books.local.ReviewDataSourceImpl
import com.terry.repository.source.books.remote.BookServiceDataSource
import com.terry.repository.source.books.remote.BookServiceDataSourceImpl
import com.terry.repository.source.calculator.HistoryDataSource
import com.terry.repository.source.calculator.HistoryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
 * Created by Taehyung Kim on 2021-07-05
 */
@InstallIn(SingletonComponent::class)
@Module(includes = [DatabaseModule::class, NetworkModule::class])
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindCalculatorHistoryDataSource(historyDataSource: HistoryDataSourceImpl): HistoryDataSource

    @Singleton
    @Binds
    fun bindCalculatorHistoryRepository(repository: HistoryRepositoryImpl): HistoryRepository

    @Singleton
    @Binds
    fun bindBookSearchHistoryDataSource(searchHistoryDataSource: BookSearchHistoryDataSourceImpl): BookSearchHistoryDataSource

    @Singleton
    @Binds
    fun bindBookSearchHistoryRepository(repository: BookSearchHistoryRepositoryImpl): BookSearchHistoryRepository

    @Singleton
    @Binds
    fun bindReviewDataSource(reviewDataSource: ReviewDataSourceImpl): ReviewDataSource

    @Singleton
    @Binds
    fun bindReviewRepository(reviewRepository: ReviewRepositoryImpl): ReviewRepository

    @Singleton
    @Binds
    fun bindBookServiceDataSource(dataSource: BookServiceDataSourceImpl): BookServiceDataSource

    @Singleton
    @Binds
    fun bindBookServiceRepository(repository: BookServiceRepositoryImpl): BookServiceRepository

    @Singleton
    @Binds
    fun bindHouseServiceDataSource(dataSource: HouseDataSourceImpl): HouseDataSource

    @Singleton
    @Binds
    fun bindHouseServiceRepository(repository: HouseRepositoryImpl): HouseRepository

}