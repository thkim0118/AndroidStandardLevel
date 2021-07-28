package com.terry.remote.model.book

import com.google.gson.annotations.SerializedName

/*
 * Created by Taehyung Kim on 2021-07-17
 */
data class BestSellerDto(
    @SerializedName("title") val title: String,
    @SerializedName("item") val books: List<Book>
)