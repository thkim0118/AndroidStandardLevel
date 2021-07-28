package com.terry.videoplayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.repository.usecase.videoplayer.GetVideoListUseCase
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-26
 */
class VideoViewModelProvider @Inject constructor(
    private val getVideoListUseCase: GetVideoListUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != VideoViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return VideoViewModel(
            getVideoListUseCase
        ) as T
    }

}