package com.terry.repository.repo.books.local

import com.terry.local.model.BookSearchHistory
import com.terry.repository.source.books.local.BookSearchHistoryDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class BookSearchHistoryRepositoryImpl @Inject constructor(
    private val searchHistoryDataSource: BookSearchHistoryDataSource
) : BookSearchHistoryRepository {
    override fun getAll(): Flow<List<BookSearchHistory>> {
        return searchHistoryDataSource.getAll()
    }

    override fun insertBookSearchHistory(bookSearchHistory: BookSearchHistory) {
        return searchHistoryDataSource.insertBookSearchHistory(bookSearchHistory)
    }

    override fun delete(keyword: String) {
        return searchHistoryDataSource.delete(keyword)
    }
}