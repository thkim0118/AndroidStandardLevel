package com.terry.repository.usecase.books

import com.terry.local.model.Review
import com.terry.repository.base.CoroutineUseCase
import com.terry.repository.repo.books.ReviewRepository
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class SaveReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) : CoroutineUseCase<Review, Unit>() {

    override suspend fun execute(parameter: Review) {
        return reviewRepository.saveReview(parameter)
    }
}