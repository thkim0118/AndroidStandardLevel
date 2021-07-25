package com.terry.remote.model.airbnb

/*
 * Created by Taehyung Kim on 2021-07-25
 */
data class HouseModel(
    val id: Int,
    val title: String,
    val price: String,
    val imgUrl: String,
    val lat: Double,
    val lng: Double
)