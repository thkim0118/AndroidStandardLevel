package com.terry.repository.repo.location

import com.terry.remote.api.LocationService
import com.terry.remote.model.location.SearchResponse
import com.terry.remote.model.location.address.AddressInfoResponse
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-08-01
 */
class LocationRepositoryImpl @Inject constructor(
    private val locationService: LocationService
) : LocationRepository {

    override suspend fun getSearchLocation(
        keyword: String,
    ): Response<SearchResponse> {
        return locationService.getSearchLocation(keyword = keyword)
    }

    override suspend fun getReverseGeoCode(
        lat: Double,
        lon: Double
    ): Response<AddressInfoResponse> {
        return locationService.getReverseGeoCode(lat = lat, lon = lon)
    }
}