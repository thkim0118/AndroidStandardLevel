package com.terry.repository.repo.books.local

import com.terry.local.model.Review
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-07-17
 */
interface ReviewRepository {
    fun getOneReview(id: Int): Flow<Review>

    suspend fun saveReview(review: Review)
}