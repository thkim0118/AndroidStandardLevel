package com.terry.repository.usecase.musicplayer

import com.terry.remote.model.audioplayer.MusicDto
import com.terry.repository.repo.musicplayer.MusicRepository
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-29
 */
class GetMusicListUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {

    suspend operator fun invoke(): Response<MusicDto> {
        return musicRepository.getMusicList()
    }

}