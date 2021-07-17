package com.terry.common.di

import com.terry.common.CoreDependency
import com.terry.repository.usecase.books.*
import com.terry.repository.usecase.books.remote.GetBestSellerBooksUseCase
import com.terry.repository.usecase.books.remote.GetBooksByNameUseCase
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
}