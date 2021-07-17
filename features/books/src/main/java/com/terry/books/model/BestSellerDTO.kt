package com.terry.books.model

import com.google.gson.annotations.SerializedName

/*
 * Created by Taehyung Kim on 2021-07-17
 */
data class BestSellerDTO(
    @SerializedName("title") val title: String,
    @SerializedName("item") val books: List<Book>
)