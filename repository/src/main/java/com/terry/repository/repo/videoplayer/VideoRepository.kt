package com.terry.repository.repo.videoplayer

import com.terry.remote.model.videoplayer.VideoDto
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-07-26
 */
interface VideoRepository {

    suspend fun getVideoList(): Response<VideoDto>
}