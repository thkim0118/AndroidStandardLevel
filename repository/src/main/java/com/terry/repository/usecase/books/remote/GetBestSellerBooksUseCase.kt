package com.terry.repository.usecase.books.remote

import com.terry.remote.model.book.BestSellerDto
import com.terry.repository.repo.books.remote.BookServiceRepository
import retrofit2.Response
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class GetBestSellerBooksUseCase @Inject constructor(
    private val bookServiceRepository: BookServiceRepository
) {

    suspend operator fun invoke(apiKey: String): Response<BestSellerDto> {
        return bookServiceRepository.getBestSellerBooks(apiKey)
    }
}