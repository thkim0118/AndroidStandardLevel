package com.terry.repository.source.books.remote

import com.terry.remote.model.book.BestSellerDTO
import com.terry.remote.model.book.SearchBookDTO
import retrofit2.Response

/*
 * Created by Taehyung Kim on 2021-07-17
 */
interface BookServiceDataSource {

    suspend fun getBooksByName(apiKey: String, keyword: String): Response<SearchBookDTO>

    suspend fun getBestSellerBooks(apiKey: String): Response<BestSellerDTO>

}