package com.thkim.calculator.di

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.thkim.calculator.viewmodel.CalculatorViewModel
import com.thkim.calculator.viewmodel.CalculatorViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/*
 * Created by Taehyung Kim on 2021-07-05
 */
@InstallIn(ActivityComponent::class)
@Module
class CalculatorModule {

    @Provides
    fun provideCalculatorViewModel(activity: Activity, factory: CalculatorViewModelFactory) =
        ViewModelProvider(
            activity as ViewModelStoreOwner,
            factory
        ).get(CalculatorViewModel::class.java)

}