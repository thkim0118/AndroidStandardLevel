package com.terry.books.viewmodel

import androidx.lifecycle.*
import com.terry.common.LogT
import com.terry.local.model.BookSearchHistory
import com.terry.remote.model.book.Book
import com.terry.repository.usecase.books.DeleteBookSearchHistoryUseCase
import com.terry.repository.usecase.books.GetAllSearchHistoryUseCase
import com.terry.repository.usecase.books.InsertBookSearchHistoryUseCase
import com.terry.repository.usecase.books.remote.GetBestSellerBooksUseCase
import com.terry.repository.usecase.books.remote.GetBooksByNameUseCase
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
    private val deleteBookSearchHistoryUseCase: DeleteBookSearchHistoryUseCase,
    private val getBooksByNameUseCase: GetBooksByNameUseCase,
    private val getBestSellerBooksUseCase: GetBestSellerBooksUseCase
) : ViewModel() {

    private val _bestSellerResponse = MutableLiveData<List<Book>>()
    val bestSellerResponse: LiveData<List<Book>>
        get() = _bestSellerResponse

    private val _searchBookResponse = MutableLiveData<List<Book>>()
    val searchBookResponse: LiveData<List<Book>>
        get() = _searchBookResponse

    fun saveSearchKeyword(keyword: String) = viewModelScope.launch {
        insertBookSearchHistoryUseCase(BookSearchHistory(null, keyword))
    }

    fun getAllBookSearchHistory() = getAllSearchHistoryUseCase(Unit).asLiveData()

    fun deleteSearchKeyword(keyword: String) = viewModelScope.launch {
        deleteBookSearchHistoryUseCase(keyword)
    }

    fun getBooksByName(apiKey: String, keyword: String) = viewModelScope.launch {
        val result = getBooksByNameUseCase(apiKey, keyword)

        try {
            if (result.isSuccessful) {
                saveSearchKeyword(keyword)

                _searchBookResponse.postValue(result.body()?.books.orEmpty())
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            LogT.e(e.stackTraceToString())
        }
    }

    fun getBestSellerBooks(apiKey: String) = viewModelScope.launch {
        val result = getBestSellerBooksUseCase(apiKey)

        try {
            if (result.isSuccessful) {
                _bestSellerResponse.postValue(result.body()?.books.orEmpty())
            }

        } catch (e: Throwable) {
            e.printStackTrace()
            LogT.e(e.stackTraceToString())
        }
    }
}