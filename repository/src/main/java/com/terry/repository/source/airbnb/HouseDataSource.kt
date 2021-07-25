package com.terry.repository.source.airbnb

import com.terry.remote.model.airbnb.HouseDTO
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-07-25
 */
interface HouseDataSource {

    suspend fun getHouseList(): Response<HouseDTO>

}