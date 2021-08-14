package com.terry.architecture.mvp.presenter

import com.terry.architecture.mvp.model.MvpModel

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class MainPresenter(
    private val view: Contract.View
) : Contract.Presenter {

    private val mvpModel: MvpModel by lazy {
        MvpModel()
    }

    override fun saveResult(first: String, second: String) {
        Thread {
            view.showProgress()

            mvpModel.saveResultData(first, second) { isSuccess, resultData ->
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