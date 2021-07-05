package com.terry.repository.base

/*
 * Created by Taehyung Kim on 2021-07-05
 */
abstract class BaseUseCase<in P, R> {
    protected abstract fun execute(parameter: P): R

    operator fun invoke(parameter: P): R {
        return execute(parameter)
    }
}