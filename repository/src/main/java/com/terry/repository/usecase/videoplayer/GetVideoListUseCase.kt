package com.terry.repository.usecase.videoplayer

import com.terry.remote.model.videoplayer.VideoDto
import com.terry.repository.repo.videoplayer.VideoRepository
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-26
 */
class GetVideoListUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) {

    suspend operator fun invoke(): Response<VideoDto> {
        return videoRepository.getVideoList()
    }

}