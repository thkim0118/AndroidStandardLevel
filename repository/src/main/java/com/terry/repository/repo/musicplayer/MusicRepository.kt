package com.terry.repository.repo.musicplayer

import com.terry.remote.model.audioplayer.MusicDto
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-07-29
 */
interface MusicRepository {

    suspend fun getMusicList(): Response<MusicDto>
}