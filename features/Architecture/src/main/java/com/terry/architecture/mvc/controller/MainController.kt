package com.terry.architecture.mvc.controller

import com.terry.architecture.mvc.model.ResultModel

/*
 * Created by Taehyung Kim on 2021-08-13
 */
class MainController {
    private var resultModel: ResultModel? = null

    fun initModel(resultModel: ResultModel) {
        this.resultModel = resultModel
    }

    fun saveResult(first: String, second: String, response: (Boolean) -> Unit) {
        val resultModel = resultModel ?: return

        resultModel.saveResultData(first, second)

        // 모델의 데이터가 변화하면 뷰에게 업데이트 상태를 알린다.
        resultModel.saveSuccess = { isSuccess ->
            response(isSuccess)
        }
    }
}
