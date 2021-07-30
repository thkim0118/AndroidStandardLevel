package com.terry.repository.source.musicplayer

import com.terry.remote.api.MusicService
import com.terry.remote.model.audioplayer.MusicDto
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-29
 */
class MusicDataSourceImpl @Inject constructor(
    private val musicService: MusicService
) : MusicDataSource {
    override suspend fun getMusicList(): Response<MusicDto> {
        return musicService.getMusicList()
    }
}