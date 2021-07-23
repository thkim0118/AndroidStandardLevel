package com.terry.transaction.home

/*
 * Created by Taehyung Kim on 2021-07-20
 */
data class ArticleModel(
    val sellerId: String,
    val title: String,
    val createdAt: Long,
    val price: String,
    val imageUrl: String
) {
    constructor() : this("", "", 0, "", "")
}
