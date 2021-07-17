package com.terry.repository.repo.books

import com.terry.local.model.Review
import com.terry.repository.source.books.ReviewDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class ReviewRepositoryImpl @Inject constructor(
    private val reviewDataSource: ReviewDataSource
) : ReviewRepository {
    override fun getOneReview(id: Int): Flow<Review> {
        return reviewDataSource.getOneReview(id)
    }

    override suspend fun saveReview(review: Review) {
        return reviewDataSource.saveReview(review)
    }
}