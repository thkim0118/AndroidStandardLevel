package com.terry.books.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.repository.usecase.books.*
import com.terry.repository.usecase.books.remote.GetBestSellerBooksUseCase
import com.terry.repository.usecase.books.remote.GetBooksByNameUseCase
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class BooksViewModelProvider @Inject constructor(
    private val getAllSearchHistoryUseCase: GetAllSearchHistoryUseCase,
    private val insertBookSearchHistoryUseCase: InsertBookSearchHistoryUseCase,
    private val deleteBookSearchHistoryUseCase: DeleteBookSearchHistoryUseCase,
    private val getBooksByNameUseCase: GetBooksByNameUseCase,
    private val getBestSellerBooksUseCase: GetBestSellerBooksUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != BooksViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return BooksViewModel(
            getAllSearchHistoryUseCase,
            insertBookSearchHistoryUseCase,
            deleteBookSearchHistoryUseCase,
            getBooksByNameUseCase,
            getBestSellerBooksUseCase
        ) as T
    }

}

class BooksDetailViewModelProvider @Inject constructor(
    private val getOneReviewUseCase: GetOneReviewUseCase,
    private val saveReviewUseCase: SaveReviewUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != BookDetailViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return BookDetailViewModel(
            getOneReviewUseCase,
            saveReviewUseCase
        ) as T
    }

}