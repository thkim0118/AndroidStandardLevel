package com.terry.repository.usecase.location

import com.terry.remote.model.location.address.AddressInfoResponse
import com.terry.repository.repo.location.LocationRepository
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-08-04
 */
class GetReverseGeoCodeUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend operator fun invoke(lat: Double, lon: Double): Response<AddressInfoResponse> {
        return locationRepository.getReverseGeoCode(lat, lon)
    }

}