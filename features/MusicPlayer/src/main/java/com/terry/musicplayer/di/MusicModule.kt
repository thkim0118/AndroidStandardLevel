package com.terry.musicplayer.di

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.terry.musicplayer.viewmodel.MusicViewModel
import com.terry.musicplayer.viewmodel.MusicViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/*
 * Created by Taehyung Kim on 2021-07-29
 */
@InstallIn(ActivityComponent::class)
@Module
class MusicModule {

    @Provides
    fun provideMusicViewModel(activity: Activity, factory: MusicViewModelProvider) =
        ViewModelProvider(
            activity as ViewModelStoreOwner,
            factory
        ).get(MusicViewModel::class.java)
}