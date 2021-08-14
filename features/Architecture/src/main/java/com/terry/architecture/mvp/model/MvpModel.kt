package com.terry.architecture.mvp.model

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class MvpModel {
    private val resultList = arrayListOf<String>()

    fun saveResultData(
        first: String,
        second: String,
        response: (Boolean, ArrayList<String>) -> Unit
    ) {
        val result = (first.toInt() + second.toInt()).toString()
        Thread.sleep(800) // Mock network delay

        val isSuccess = resultList.add(result + "\n")

        response(isSuccess, resultList)
    }
}