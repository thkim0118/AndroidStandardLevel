package com.terry.common.di

import com.terry.common.CoreDependency
import com.terry.repository.usecase.DeleteAllHistoryUseCase
import com.terry.repository.usecase.GetAllHistoryUseCase
import com.terry.repository.usecase.InsertHistoryUseCase
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
}