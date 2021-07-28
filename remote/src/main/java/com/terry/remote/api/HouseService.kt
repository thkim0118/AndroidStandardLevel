package com.terry.remote.api

import com.terry.remote.model.airbnb.HouseDto
import retrofit2.Response
import retrofit2.http.GET

/*
 * Created by Taehyung Kim on 2021-07-25
 */
interface HouseService {

    @GET("/v3/cad01917-4711-4b16-8fc4-da32084f6d98")
    suspend fun getHouseList(): Response<HouseDto>

}