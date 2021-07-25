package com.terry.airbnb.di

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.terry.airbnb.viewmodel.HouseMainViewModel
import com.terry.airbnb.viewmodel.HouseMainViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/*
 * Created by Taehyung Kim on 2021-07-25
 */
@InstallIn(ActivityComponent::class)
@Module
class HouseModule {

    @Provides
    fun provideHouseMainViewModel(activity: Activity, factory: HouseMainViewModelProvider) =
        ViewModelProvider(
            activity as ViewModelStoreOwner,
            factory
        ).get(HouseMainViewModel::class.java)
}