package com.terry.repository.usecase.books

import com.terry.local.model.BookSearchHistory
import com.terry.repository.base.BaseUseCase
import com.terry.repository.repo.books.local.BookSearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class GetAllSearchHistoryUseCase @Inject constructor(
    private val searchHistoryRepository: BookSearchHistoryRepository
) : BaseUseCase<Unit, Flow<List<BookSearchHistory>>>() {

    override fun execute(parameter: Unit): Flow<List<BookSearchHistory>> {
        return searchHistoryRepository.getAll()
    }
}