package com.terry.musicplayer

import android.os.Bundle
import com.terry.musicplayer.databinding.ActivityAudioMainBinding
import com.terry.common.base.BaseActivity
import com.terry.common.di.UseCaseDependencies
import com.terry.musicplayer.di.DaggerMusicComponent
import com.terry.musicplayer.viewmodel.MusicViewModel
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class MusicMainActivity :
    BaseActivity<ActivityAudioMainBinding>(ActivityAudioMainBinding::inflate) {

    @Inject
    lateinit var viewModel: MusicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PlayerFragment.newInstance())
            .commit()
    }

    private fun initCoreDependentInjection() {
        val useCaseDependencies = EntryPointAccessors.fromApplication(
            applicationContext,
            UseCaseDependencies::class.java
        )

        DaggerMusicComponent.factory().create(
            dependencies = useCaseDependencies,
            activity = this
        ).inject(this)
    }
}