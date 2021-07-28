package com.terry.repository.source.videoplayer

import com.terry.remote.api.VideoService
import com.terry.remote.model.videoplayer.VideoDto
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-26
 */
class VideoDataSourceImpl @Inject constructor(
    private val videoService: VideoService
) : VideoDataSource {
    override suspend fun getVideoList(): Response<VideoDto> {
        return videoService.getVideoList()
    }
}