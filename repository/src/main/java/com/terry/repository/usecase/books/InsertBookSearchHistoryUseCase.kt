package com.terry.repository.usecase.books

import com.terry.local.model.BookSearchHistory
import com.terry.repository.base.CoroutineUseCase
import com.terry.repository.repo.books.BookSearchHistoryRepository
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class InsertBookSearchHistoryUseCase @Inject constructor(
    private val repository: BookSearchHistoryRepository
) : CoroutineUseCase<BookSearchHistory, Unit>() {

    override suspend fun execute(parameter: BookSearchHistory) {
        return repository.insertBookSearchHistory(parameter)
    }
}