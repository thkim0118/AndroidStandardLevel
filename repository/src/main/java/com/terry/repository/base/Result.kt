package com.terry.repository.base

/*
 * Created by Taehyung Kim on 2021-07-05
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T?) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()
}