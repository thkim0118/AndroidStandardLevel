package com.terry.repository.repo.location

import com.terry.remote.model.location.SearchResponse
import com.terry.remote.model.location.address.AddressInfoResponse
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-08-01
 */
interface LocationRepository {

    suspend fun getSearchLocation(
        keyword: String,
    ): Response<SearchResponse>

    suspend fun getReverseGeoCode(
        lat: Double,
        lon: Double,
    ): Response<AddressInfoResponse>

}