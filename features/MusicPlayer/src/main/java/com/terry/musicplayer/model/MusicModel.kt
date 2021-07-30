package com.terry.musicplayer.model

/*
 * Created by Taehyung Kim on 2021-07-29
 */
data class MusicModel(
    val id: Long,
    val track: String,
    val streamUrl: String,
    val artist: String,
    val coverUrl: String,
    val isPlaying: Boolean = false
)
