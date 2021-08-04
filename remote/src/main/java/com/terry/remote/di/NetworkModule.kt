package com.terry.remote.di

import com.terry.remote.BuildConfig
import com.terry.remote.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val INTER_PARK_BASE_URL = "https://book.interpark.com"
    private const val MOCKY_BASE_URL = "https://run.mocky.io"
    private const val TMAP_BASE_URL = "https://apis.openapi.sk.com"

    @BookRetrofit
    @Singleton
    @Provides
    fun provideBookRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(INTER_PARK_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @MockRetrofit
    @Singleton
    @Provides
    fun provideMockRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(MOCKY_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @LocationRetrofit
    @Singleton
    @Provides
    fun provideTmapRetrofit(@LocationOkHttpClient tmapHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(TMAP_BASE_URL)
            .client(tmapHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideBookService(@BookRetrofit retrofit: Retrofit): BookService =
        retrofit.create(BookService::class.java)

    @Singleton
    @Provides
    fun provideHouseService(@MockRetrofit retrofit: Retrofit): HouseService =
        retrofit.create(HouseService::class.java)

    @Singleton
    @Provides
    fun provideVideoService(@MockRetrofit retrofit: Retrofit): VideoService =
        retrofit.create(VideoService::class.java)

    @Singleton
    @Provides
    fun provideAudioService(@MockRetrofit retrofit: Retrofit): MusicService =
        retrofit.create(MusicService::class.java)

    @Singleton
    @Provides
    fun provideLocationService(@LocationRetrofit retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)

    @LocationOkHttpClient
    @Singleton
    @Provides
    fun provideTmapOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .run {
                addInterceptor { chain ->
                    chain.proceed(
                        chain.request()
                            .newBuilder()
                            .header("appKey", BuildConfig.tmap_project_id)
                            .build()
                    )
                }
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                })
                connectTimeout(5, TimeUnit.SECONDS)
                build()
            }
}