package com.terry.location.di

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.terry.location.viewmodel.LocationViewModel
import com.terry.location.viewmodel.LocationViewModelProvider
import com.terry.location.viewmodel.MapViewModel
import com.terry.location.viewmodel.MapViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/*
 * Created by Taehyung Kim on 2021-08-01
 */
@InstallIn(ActivityComponent::class)
@Module
class LocationModule {

    @Provides
    fun provideVideoViewModel(activity: Activity, factory: LocationViewModelProvider) =
        ViewModelProvider(
            activity as ViewModelStoreOwner,
            factory
        ).get(LocationViewModel::class.java)

    @Provides
    fun provideMapViewModel(activity: Activity, factory: MapViewModelProvider) =
        ViewModelProvider(
            activity as ViewModelStoreOwner,
            factory
        ).get(MapViewModel::class.java)
}
