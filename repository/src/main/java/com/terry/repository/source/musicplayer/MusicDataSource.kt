package com.terry.repository.source.musicplayer

import com.terry.remote.model.audioplayer.MusicDto
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-07-29
 */
interface MusicDataSource {

    suspend fun getMusicList(): Response<MusicDto>
}