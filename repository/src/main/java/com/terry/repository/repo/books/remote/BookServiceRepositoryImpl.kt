package com.terry.repository.repo.books.remote

import com.terry.remote.model.book.BestSellerDto
import com.terry.remote.model.book.SearchBookDto
import com.terry.repository.source.books.remote.BookServiceDataSource
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class BookServiceRepositoryImpl @Inject constructor(
    private val bookServiceDataSource: BookServiceDataSource
) : BookServiceRepository {
    override suspend fun getBooksByName(apiKey: String, keyword: String): Response<SearchBookDto> {
        return bookServiceDataSource.getBooksByName(apiKey, keyword)
    }

    override suspend fun getBestSellerBooks(apiKey: String): Response<BestSellerDto> {
        return bookServiceDataSource.getBestSellerBooks(apiKey)
    }
}