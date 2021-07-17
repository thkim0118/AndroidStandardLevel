package com.terry.repository.source.books

import com.terry.local.model.Review
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-07-17
 */
interface ReviewDataSource {
    fun getOneReview(id: Int): Flow<Review>

    suspend fun saveReview(review: Review)
}