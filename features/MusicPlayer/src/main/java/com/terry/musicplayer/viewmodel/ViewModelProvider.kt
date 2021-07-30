package com.terry.musicplayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.repository.usecase.musicplayer.GetMusicListUseCase
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-29
 */
class MusicViewModelProvider @Inject constructor(
    private val getMusicListUseCase: GetMusicListUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != MusicViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return MusicViewModel(
            getMusicListUseCase
        ) as T
    }
}