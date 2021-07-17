package com.terry.books.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.terry.local.model.Review
import com.terry.repository.usecase.books.GetOneReviewUseCase
import com.terry.repository.usecase.books.SaveReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getOneReviewUseCase: GetOneReviewUseCase,
    private val saveReviewUseCase: SaveReviewUseCase
) : ViewModel() {

    fun getOneReview(id: Int) = getOneReviewUseCase(id).asLiveData()

    fun saveReview(review: Review) = viewModelScope.launch {
        saveReviewUseCase(review)
    }

}