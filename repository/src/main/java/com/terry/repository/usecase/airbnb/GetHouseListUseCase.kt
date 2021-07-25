package com.terry.repository.usecase.airbnb

import com.terry.remote.model.airbnb.HouseDTO
import com.terry.repository.repo.airbnb.HouseRepository
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-25
 */
class GetHouseListUseCase @Inject constructor(
    private val houseRepository: HouseRepository
) {

    suspend operator fun invoke(): Response<HouseDTO> {
        return houseRepository.getHouseList()
    }

}