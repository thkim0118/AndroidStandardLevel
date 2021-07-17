package com.terry.repository.source.books.remote

import com.terry.remote.api.BookService
import com.terry.remote.model.BestSellerDTO
import com.terry.remote.model.SearchBookDTO
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class BookServiceDataSourceImpl @Inject constructor(
    private val bookService: BookService
) : BookServiceDataSource {
    override suspend fun getBooksByName(apiKey: String, keyword: String): Response<SearchBookDTO> {
        return bookService.getBooksByName(apiKey, keyword)
    }

    override suspend fun getBestSellerBooks(apiKey: String): Response<BestSellerDTO> {
        return bookService.getBestSellerBooks(apiKey)
    }
}