package com.terry.repository.source.books.local

import com.terry.local.dao.BookSearchHistoryDao
import com.terry.local.model.BookSearchHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class BookSearchHistoryDataSourceImpl @Inject constructor(
    private val searchHistoryDao: BookSearchHistoryDao
) : BookSearchHistoryDataSource {
    override fun getAll(): Flow<List<BookSearchHistory>> {
        return searchHistoryDao.getAll()
    }

    override fun insertBookSearchHistory(bookSearchHistory: BookSearchHistory) {
        return searchHistoryDao.insertBookSearchHistory(bookSearchHistory)
    }

    override fun delete(keyword: String) {
        return searchHistoryDao.delete(keyword)
    }
}