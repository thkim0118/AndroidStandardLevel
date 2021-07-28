package com.terry.repository.repo.books.remote

import com.terry.remote.model.book.BestSellerDto
import com.terry.remote.model.book.SearchBookDto
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-07-17
 */
interface BookServiceRepository {

    suspend fun getBooksByName(apiKey: String, keyword: String): Response<SearchBookDto>

    suspend fun getBestSellerBooks(apiKey: String): Response<BestSellerDto>

}