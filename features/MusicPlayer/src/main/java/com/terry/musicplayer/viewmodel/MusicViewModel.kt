package com.terry.musicplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terry.common.LogT
import com.terry.remote.model.audioplayer.MusicDto
import com.terry.repository.usecase.musicplayer.GetMusicListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-29
 */
@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getMusicListUseCase: GetMusicListUseCase
) : ViewModel() {

    private val _musicList = MutableLiveData<MusicDto>()
    val musicList: LiveData<MusicDto>
        get() = _musicList

    fun getMusicList() = viewModelScope.launch {
        val result = getMusicListUseCase()

        try {
            if (result.isSuccessful) {
                result.body()?.let { musicDto ->
                    _musicList.postValue(musicDto)
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            LogT.e(e.stackTraceToString())
        }
    }

}