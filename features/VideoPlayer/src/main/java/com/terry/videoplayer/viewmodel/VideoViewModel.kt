package com.terry.videoplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terry.common.LogT
import com.terry.remote.model.videoplayer.VideoDto
import com.terry.repository.usecase.videoplayer.GetVideoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-26
 */
@HiltViewModel
class VideoViewModel @Inject constructor(
    private val getVideoListUseCase: GetVideoListUseCase
) : ViewModel() {

    private val _videoList = MutableLiveData<VideoDto>()
    val videoList: LiveData<VideoDto>
        get() = _videoList

    fun getVideoList() = viewModelScope.launch {
        val result = getVideoListUseCase()

        try {
            if (result.isSuccessful.not()) {
                LogT.d("result is failed.")
                return@launch
            }

            result.body()?.let {
                LogT.d(it.videos.toString())
                _videoList.postValue(it)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            LogT.e(e.stackTraceToString())
        }
    }

}