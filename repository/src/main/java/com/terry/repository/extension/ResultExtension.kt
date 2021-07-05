package com.terry.repository.extension

import com.terry.repository.base.Result

/*
 * Created by Taehyung Kim on 2021-07-05
 */
suspend fun <T> Result<T>.onSuccess(onSuccess: suspend (it: T?) -> Unit) {
    if (this is Result.Success) onSuccess(this.data)
}

suspend fun Result<*>.onError(onError: suspend (it: Throwable) -> Unit) {
    if (this is Result.Error) onError(this.error)
}