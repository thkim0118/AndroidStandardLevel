package com.terry.remote.model.audioplayer

import com.google.gson.annotations.SerializedName

/*
 * Created by Taehyung Kim on 2021-07-28
 */
data class MusicEntity(
    @SerializedName("track")
    val track: String,
    @SerializedName("streamUrl")
    val streamUrl: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("coverUrl")
    val coverUrl: String
)
