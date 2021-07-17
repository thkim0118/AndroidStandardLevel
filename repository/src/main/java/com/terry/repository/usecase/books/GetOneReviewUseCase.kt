package com.terry.repository.usecase.books

import com.terry.local.model.Review
import com.terry.repository.base.BaseUseCase
import com.terry.repository.repo.books.local.ReviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class GetOneReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) : BaseUseCase<Int, Flow<Review>>() {

    override fun execute(parameter: Int): Flow<Review> {
        return reviewRepository.getOneReview(parameter)
    }
}