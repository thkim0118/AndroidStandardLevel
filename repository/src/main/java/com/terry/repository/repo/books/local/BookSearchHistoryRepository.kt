package com.terry.repository.repo.books.local

import com.terry.local.model.BookSearchHistory
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-07-17
 */
interface BookSearchHistoryRepository {

    fun getAll(): Flow<List<BookSearchHistory>>

    fun insertBookSearchHistory(bookSearchHistory: BookSearchHistory)

    fun delete(keyword: String)

}