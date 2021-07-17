package com.terry.common.di

import com.terry.common.CoreDependency
import com.terry.repository.usecase.books.DeleteBookSearchHistoryUseCase
import com.terry.repository.usecase.books.GetAllSearchHistoryUseCase
import com.terry.repository.usecase.books.InsertBookSearchHistoryUseCase
import com.terry.repository.usecase.calculator.DeleteAllHistoryUseCase
import com.terry.repository.usecase.calculator.GetAllHistoryUseCase
import com.terry.repository.usecase.calculator.InsertHistoryUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
 * Created by Taehyung Kim on 2021-07-05
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface CoreModuleDependencies {

    fun coreDependency(): CoreDependency

    fun getHistoryAllUseCase(): GetAllHistoryUseCase

    fun insertHistoryUseCase(): InsertHistoryUseCase

    fun deleteAllHistoryUseCase(): DeleteAllHistoryUseCase

    fun getBookSearchHistoryAllUseCase(): GetAllSearchHistoryUseCase

    fun insertBookSearchHistoryUseCase(): InsertBookSearchHistoryUseCase

    fun deleteBookSearchHistoryUSeCase(): DeleteBookSearchHistoryUseCase
}