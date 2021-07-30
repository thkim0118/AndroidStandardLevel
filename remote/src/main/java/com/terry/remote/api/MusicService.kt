package com.terry.remote.api

import com.terry.remote.model.audioplayer.MusicDto
import retrofit2.Response
import retrofit2.http.GET

/*
 * Created by Taehyung Kim on 2021-07-29
 */
interface MusicService {

    @GET("/v3/980fa618-95fe-49d4-9f4e-8f5bf4d1a74f")
    suspend fun getMusicList(): Response<MusicDto>
}