package com.terry.architecture.mvp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.terry.architecture.R
import com.terry.architecture.mvp.model.MvpModel
import com.terry.architecture.mvp.presenter.MainPresenter

class MvpMainActivity : AppCompatActivity() {

    private val mvpModel: MvpModel by lazy {
        MvpModel()
    }

    private val mainPresenter: MainPresenter by lazy {
        MainPresenter()
    }

    private val makeResultButton by lazy {
        findViewById<Button>(R.id.makeResultButton)
    }
    private val firstEditText by lazy {
        findViewById<EditText>(R.id.firstInputEditText)
    }
    private val secondEditText by lazy {
        findViewById<EditText>(R.id.secondInputEditText)
    }
    private val resultTextView by lazy {
        findViewById<TextView>(R.id.resultTextView)
    }
    private val progressBar by lazy {
        findViewById<ProgressBar>(R.id.progressBar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_main)

        mainPresenter.initModel(mvpModel)
        /*
         * TODO :
         *  1. 버튼을 누르면 프레젠터로 전달
         *  2. 프레젠터에서 뷰에게 대기상태를 알린다.
         *  3. 프레젠터는 모델에게 데이터가 필요하다고 알린다.
         *  4. 모델이 데이터를 가져와 프레젠터에게 넘긴다.
         *  5. 뷰에 데이터 수집 성공 유무를 알린다.
         *  6. 프레젠터가 데이터 표출 방식을 가공한다.
         *  7. 뷰가 데이터를 프레젠터로부터 가져온다.
         */
        bindViews()
    }

    private fun bindViews() {
        makeResultButton.setOnClickListener {
            showProgress()

            mainPresenter.saveResult(
                first = firstEditText.text.toString(),
                second = secondEditText.text.toString()
            )
        }
    }

    private fun showProgress() {
        Handler(Looper.getMainLooper()).post {
            progressBar.isVisible = true
        }
    }

    private fun hideProgress() {
        Handler(Looper.getMainLooper()).post {
            progressBar.isVisible = false
        }
    }

}