package com.terry.repository.repo.airbnb

import com.terry.remote.model.airbnb.HouseDTO
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-07-25
 */
interface HouseRepository {

    suspend fun getHouseList(): Response<HouseDTO>

}