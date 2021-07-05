package com.terry.repository.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*
 * Created by Taehyung Kim on 2021-07-05
 */
abstract class CoroutineUseCase<in P, R> {
    protected abstract suspend fun execute(parameter: P): R

    suspend operator fun invoke(parameter: P): R {
        return withContext(Dispatchers.IO) {
            execute(parameter)
        }
    }
}