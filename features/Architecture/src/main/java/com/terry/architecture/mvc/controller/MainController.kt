package com.terry.architecture.mvc.controller

import com.terry.architecture.mvc.MvcMainActivity
import com.terry.architecture.mvc.model.ResultModel

/*
 * Created by Taehyung Kim on 2021-08-13
 */
class MainController {
    private var mainActivity: MvcMainActivity? = null
    private var resultModel: ResultModel? = null

    fun initView(mainActivity: MvcMainActivity) {
        this.mainActivity = mainActivity
    }

    fun initModel(resultModel: ResultModel) {
        this.resultModel = resultModel
    }

    fun saveResult(first: String, second: String) {
        val mainActivity = mainActivity ?: return
        val resultModel = resultModel ?: return

        mainActivity.showProgress()

        resultModel.saveResultData(first, second)
    }

    fun invokeSuccessResult() {
        val mainActivity = mainActivity ?: return

        mainActivity.hideProgress()
        mainActivity.showResult()
    }
}
