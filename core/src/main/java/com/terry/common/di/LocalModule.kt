package com.terry.common.di

import com.terry.local.di.DatabaseModule
import com.terry.repository.repo.books.BookSearchHistoryRepository
import com.terry.repository.repo.books.BookSearchHistoryRepositoryImpl
import com.terry.repository.repo.books.ReviewRepository
import com.terry.repository.repo.books.ReviewRepositoryImpl
import com.terry.repository.repo.calculator.HistoryRepository
import com.terry.repository.repo.calculator.HistoryRepositoryImpl
import com.terry.repository.source.books.BookSearchHistoryDataSource
import com.terry.repository.source.books.BookSearchHistoryDataSourceImpl
import com.terry.repository.source.books.ReviewDataSource
import com.terry.repository.source.books.ReviewDataSourceImpl
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
@Module(includes = [DatabaseModule::class])
interface LocalModule {

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

}