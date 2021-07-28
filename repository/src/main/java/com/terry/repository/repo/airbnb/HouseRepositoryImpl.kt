package com.terry.repository.repo.airbnb

import com.terry.remote.model.airbnb.HouseDto
import com.terry.repository.source.airbnb.HouseDataSource
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-25
 */
class HouseRepositoryImpl @Inject constructor(
    private val houseDataSource: HouseDataSource
) : HouseRepository {

    override suspend fun getHouseList(): Response<HouseDto> {
        return houseDataSource.getHouseList()
    }
}