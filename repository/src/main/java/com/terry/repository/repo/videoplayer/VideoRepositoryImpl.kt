package com.terry.repository.repo.videoplayer

import com.terry.remote.model.videoplayer.VideoDto
import com.terry.repository.source.videoplayer.VideoDataSource
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-26
 */
class VideoRepositoryImpl @Inject constructor(
    private val videoDataSource: VideoDataSource
) : VideoRepository {
    override suspend fun getVideoList(): Response<VideoDto> {
        return videoDataSource.getVideoList()
    }
}