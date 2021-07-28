package com.terry.remote.api

import com.terry.remote.model.book.BestSellerDto
import com.terry.remote.model.book.SearchBookDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Created by Taehyung Kim on 2021-07-17
 */
interface BookService {

    @GET("/api/search.api?output=json")
    suspend fun getBooksByName(
        @Query("key") apiKey: String,
        @Query("query") keyword: String
    ): Response<SearchBookDto>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    suspend fun getBestSellerBooks(
        @Query("key") apiKey: String
    ): Response<BestSellerDto>

}