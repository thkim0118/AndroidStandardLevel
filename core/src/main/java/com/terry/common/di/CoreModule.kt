package com.terry.common.di

import com.terry.common.CoreDependency
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
 * Created by Taehyung Kim on 2021-07-05
 */
@InstallIn(SingletonComponent::class)
@Module(includes = [LocalModule::class])
class CoreModule {

    @Provides
    fun provideCoreDependency() = CoreDependency()

}