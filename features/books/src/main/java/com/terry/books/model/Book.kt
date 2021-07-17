package com.terry.books.model

import com.google.gson.annotations.SerializedName

/*
 * Created by Taehyung Kim on 2021-07-17
 */
data class Book(
    @SerializedName("itemId") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String
)
