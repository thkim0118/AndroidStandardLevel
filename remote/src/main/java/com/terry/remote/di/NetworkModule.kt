package com.terry.remote.di

import com.terry.remote.api.BookService
import com.terry.remote.api.HouseService
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
    private const val HOUSE_BASE_URL = "https://run.mocky.io"

    @BookRetrofit
    @Singleton
    @Provides
    fun provideBookRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(INTER_PARK_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @HouseRetrofit
    @Singleton
    @Provides
    fun provideHouseRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(HOUSE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideBookService(@BookRetrofit retrofit: Retrofit): BookService =
        retrofit.create(BookService::class.java)

    @Singleton
    @Provides
    fun provideHouseService(@HouseRetrofit retrofit: Retrofit): HouseService =
        retrofit.create(HouseService::class.java)
}