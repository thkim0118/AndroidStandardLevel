package com.terry.remote.di

import com.terry.remote.api.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val INTER_PARK_BASE_URL = "https://book.interpark.com"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(INTER_PARK_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideBookService(retrofit: Retrofit): BookService =
        retrofit.create(BookService::class.java)
}