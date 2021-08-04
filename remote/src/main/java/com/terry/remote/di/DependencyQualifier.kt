package com.terry.remote.di

import javax.inject.Qualifier

/*
 * Created by Taehyung Kim on 2021-07-25
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BookRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MockRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocationRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocationOkHttpClient
