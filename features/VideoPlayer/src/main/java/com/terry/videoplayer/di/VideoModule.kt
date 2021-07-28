package com.terry.videoplayer.di

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.terry.videoplayer.viewmodel.VideoViewModel
import com.terry.videoplayer.viewmodel.VideoViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/*
 * Created by Taehyung Kim on 2021-07-26
 */
@InstallIn(ActivityComponent::class)
@Module
class VideoModule {

    @Provides
    fun provideVideoViewModel(activity: Activity, factory: VideoViewModelProvider) =
        ViewModelProvider(
            activity as ViewModelStoreOwner,
            factory
        ).get(VideoViewModel::class.java)

}