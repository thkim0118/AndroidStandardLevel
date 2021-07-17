package com.terry.books.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.terry.local.model.BookSearchHistory
import com.terry.repository.usecase.books.DeleteBookSearchHistoryUseCase
import com.terry.repository.usecase.books.GetAllSearchHistoryUseCase
import com.terry.repository.usecase.books.InsertBookSearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getAllSearchHistoryUseCase: GetAllSearchHistoryUseCase,
    private val insertBookSearchHistoryUseCase: InsertBookSearchHistoryUseCase,
    private val deleteBookSearchHistoryUseCase: DeleteBookSearchHistoryUseCase
) : ViewModel() {

    fun saveSearchKeyword(keyword: String) = viewModelScope.launch {
        insertBookSearchHistoryUseCase(BookSearchHistory(null, keyword))
    }

    fun getAllBookSearchHistory() = getAllSearchHistoryUseCase(Unit).asLiveData()

    fun deleteSearchKeyword(keyword: String) = viewModelScope.launch {
        deleteBookSearchHistoryUseCase(keyword)
    }
}