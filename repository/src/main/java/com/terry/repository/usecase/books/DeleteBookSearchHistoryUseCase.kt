package com.terry.repository.usecase.books

import com.terry.repository.base.CoroutineUseCase
import com.terry.repository.repo.books.local.BookSearchHistoryRepository
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class DeleteBookSearchHistoryUseCase @Inject constructor(
    private val repository: BookSearchHistoryRepository
) : CoroutineUseCase<String, Unit>() {
    override suspend fun execute(parameter: String) {
        return repository.delete(parameter)
    }
}