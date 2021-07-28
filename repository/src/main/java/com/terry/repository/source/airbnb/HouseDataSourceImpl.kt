package com.terry.repository.source.airbnb

import com.terry.remote.api.HouseService
import com.terry.remote.model.airbnb.HouseDto
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-25
 */
class HouseDataSourceImpl @Inject constructor(
    private val houseService: HouseService
) : HouseDataSource {

    override suspend fun getHouseList(): Response<HouseDto> {
        return houseService.getHouseList()
    }
}