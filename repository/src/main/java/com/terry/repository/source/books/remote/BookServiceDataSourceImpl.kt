package com.terry.repository.source.books.remote

import com.terry.remote.api.BookService
import com.terry.remote.model.book.BestSellerDto
import com.terry.remote.model.book.SearchBookDto
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class BookServiceDataSourceImpl @Inject constructor(
    private val bookService: BookService
) : BookServiceDataSource {
    override suspend fun getBooksByName(apiKey: String, keyword: String): Response<SearchBookDto> {
        return bookService.getBooksByName(apiKey, keyword)
    }

    override suspend fun getBestSellerBooks(apiKey: String): Response<BestSellerDto> {
        return bookService.getBestSellerBooks(apiKey)
    }
}