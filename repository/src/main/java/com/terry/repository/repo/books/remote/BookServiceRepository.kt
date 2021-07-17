package com.terry.repository.repo.books.remote

import com.terry.remote.model.BestSellerDTO
import com.terry.remote.model.SearchBookDTO
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-07-17
 */
interface BookServiceRepository {

    suspend fun getBooksByName(apiKey: String, keyword: String): Response<SearchBookDTO>

    suspend fun getBestSellerBooks(apiKey: String): Response<BestSellerDTO>

}