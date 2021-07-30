package com.terry.musicplayer.di

import android.app.Activity
import com.terry.common.di.UseCaseDependencies
import com.terry.musicplayer.MusicMainActivity
import com.terry.musicplayer.PlayerFragment
import dagger.BindsInstance
import dagger.Component

/*
 * Created by Taehyung Kim on 2021-07-29
 */
@Component(
    dependencies = [UseCaseDependencies::class],
    modules = [MusicModule::class]
)
interface MusicComponent {

    fun inject(musicMainActivity: MusicMainActivity)
    fun inject(playerFragment: PlayerFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: UseCaseDependencies,
            @BindsInstance activity: Activity
        ): MusicComponent
    }

}