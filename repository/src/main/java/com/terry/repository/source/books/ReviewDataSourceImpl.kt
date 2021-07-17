package com.terry.repository.source.books

import com.terry.local.dao.ReviewDao
import com.terry.local.model.Review
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class ReviewDataSourceImpl @Inject constructor(
    private val reviewDao: ReviewDao
) : ReviewDataSource {
    override fun getOneReview(id: Int): Flow<Review> {
        return reviewDao.getOneReview(id)
    }

    override suspend fun saveReview(review: Review) {
        return reviewDao.saveReview(review)
    }
}