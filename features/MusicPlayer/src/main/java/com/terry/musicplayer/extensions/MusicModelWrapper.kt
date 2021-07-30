package com.terry.musicplayer.extensions

import com.terry.musicplayer.PlayerModel
import com.terry.musicplayer.model.MusicModel
import com.terry.remote.model.audioplayer.MusicDto
import com.terry.remote.model.audioplayer.MusicEntity

/*
 * Created by Taehyung Kim on 2021-07-30
 */
fun MusicEntity.mapper(id: Long): MusicModel =
    MusicModel(
        id = id,
        streamUrl = this.streamUrl,
        coverUrl = this.coverUrl,
        track = this.track,
        artist = this.artist
    )

fun MusicDto.mapper(): PlayerModel =
    PlayerModel(
        playMusicList = musics.mapIndexed { index, musicEntity ->
            musicEntity.mapper(index.toLong())
        }
    )