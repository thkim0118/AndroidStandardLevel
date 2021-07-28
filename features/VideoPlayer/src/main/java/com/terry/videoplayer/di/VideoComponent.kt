package com.terry.videoplayer.di

import android.app.Activity
import com.terry.common.di.UseCaseDependencies
import com.terry.videoplayer.PlayerFragment
import com.terry.videoplayer.VideoMainActivity
import dagger.BindsInstance
import dagger.Component

/*
 * Created by Taehyung Kim on 2021-07-26
 */
@Component(
    dependencies = [UseCaseDependencies::class],
    modules = [VideoModule::class]
)
interface VideoComponent {

    fun inject(videoMainActivity: VideoMainActivity)
    fun inject(playerFragment: PlayerFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dependentModule: UseCaseDependencies,
            @BindsInstance activity: Activity
        ): VideoComponent
    }
}