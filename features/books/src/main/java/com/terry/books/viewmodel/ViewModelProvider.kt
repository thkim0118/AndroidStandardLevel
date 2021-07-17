package com.terry.books.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terry.repository.usecase.books.DeleteBookSearchHistoryUseCase
import com.terry.repository.usecase.books.GetAllSearchHistoryUseCase
import com.terry.repository.usecase.books.InsertBookSearchHistoryUseCase
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class BooksViewModelProvider @Inject constructor(
    private val getAllSearchHistoryUseCase: GetAllSearchHistoryUseCase,
    private val insertBookSearchHistoryUseCase: InsertBookSearchHistoryUseCase,
    private val deleteBookSearchHistoryUseCase: DeleteBookSearchHistoryUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != BooksViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }

        return BooksViewModel(
            getAllSearchHistoryUseCase,
            insertBookSearchHistoryUseCase,
            deleteBookSearchHistoryUseCase
        ) as T
    }

}