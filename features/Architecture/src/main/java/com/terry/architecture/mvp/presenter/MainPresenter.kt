package com.terry.architecture.mvp.presenter

import com.terry.architecture.mvp.model.MvpModel

/*
 * Created by Taehyung Kim on 2021-08-14
 */
class MainPresenter {

    private lateinit var mvpModel: MvpModel

    fun initModel(mvpModel: MvpModel) {
        this.mvpModel = mvpModel
    }

    fun saveResult(first: String, second: String) {

    }
}