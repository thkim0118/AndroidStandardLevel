package com.terry.repository.usecase.location

import com.terry.remote.model.location.SearchResponse
import com.terry.repository.repo.location.LocationRepository
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-08-01
 */
class GetSearchLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend operator fun invoke(keyword: String): Response<SearchResponse> {
        return locationRepository.getSearchLocation(keyword)
    }

}