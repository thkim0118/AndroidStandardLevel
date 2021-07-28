package com.terry.remote.api

import com.terry.remote.model.videoplayer.VideoDto
import retrofit2.Response
import retrofit2.http.GET

/*
 * Created by Taehyung Kim on 2021-07-26
 */
interface VideoService {

    @GET("/v3/dffaa1f9-732f-4ff5-b028-c9f2a5ed2518")
    suspend fun getVideoList(): Response<VideoDto>
}