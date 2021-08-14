package com.terry.architecture.mvp.presenter

import com.terry.architecture.ResultModel

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class MainPresenter(
    private val view: Contract.View
) : Contract.Presenter {

    private val resultModel: ResultModel by lazy {
        ResultModel()
    }

    override fun saveResult(first: String, second: String) {
        Thread {
            view.showProgress()

            resultModel.saveResultData(first, second) { isSuccess, resultData ->
                view.hideProgress()

                if (isSuccess) {
                    view.showResultData(getAllResult(resultData))
                }
            }
        }.start()
    }

    private fun getAllResult(resultList: ArrayList<String>) =
        resultList.reduce { total, s -> total + s }

}