package com.terry.repository.repo.musicplayer

import com.terry.remote.model.audioplayer.MusicDto
import com.terry.repository.source.musicplayer.MusicDataSource
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-29
 */
class MusicRepositoryImpl @Inject constructor(
    private val musicDataSource: MusicDataSource
) : MusicRepository {
    override suspend fun getMusicList(): Response<MusicDto> {
        return musicDataSource.getMusicList()
    }
}