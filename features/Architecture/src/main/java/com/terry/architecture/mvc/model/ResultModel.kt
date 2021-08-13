package com.terry.architecture.mvc.model

import com.terry.architecture.mvc.controller.MainController

/*
 * Created by Taehyung Kim on 2021-08-12
 */
class ResultModel(
    private val mainController: MainController
) {
    private val resultList = arrayListOf<String>()

    fun saveResultData(first: String, second: String) {
        Thread {
            Thread.sleep(800) // Mock network delay

            val isSuccess = resultList.add((first.toInt() + second.toInt()).toString() + "\n")

            if (isSuccess) {
                mainController.invokeSuccessResult()
            }
        }.start()
    }

    fun getTotalResult(): String = resultList.reduce { total, s -> total + s }

}
