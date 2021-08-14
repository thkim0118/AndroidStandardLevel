package com.terry.architecture.mvc.model

/*
 * Created by Taehyung Kim on 2021-08-12
 */
class MvcModel {
    private val resultList = arrayListOf<String>()

    var saveSuccess: ((Boolean) -> Unit)? = null

    fun saveResultData(first: String, second: String) {
        Thread {
            val result = (first.toInt() + second.toInt()).toString()
            Thread.sleep(800) // Mock network delay

            val isSuccess = resultList.add(result + "\n")

            // Alert Model Changed
            saveSuccess?.invoke(isSuccess)
        }.start()
    }

    fun getTotalResult(): String = resultList.reduce { total, s -> total + s }

}
