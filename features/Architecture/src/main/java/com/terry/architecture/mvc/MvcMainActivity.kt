package com.terry.architecture.mvc

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
import com.terry.architecture.mvc.controller.MainController
import com.terry.architecture.mvc.model.ResultModel

class MvcMainActivity : AppCompatActivity() {

    private lateinit var mainController: MainController
    private lateinit var resultModel: ResultModel

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

        mainController = MainController()
        resultModel = ResultModel(mainController)

        with(mainController) {
            initView(this@MvcMainActivity)
            initModel(resultModel)
        }

        bindViews()
    }

    fun showResult() {
        Handler(Looper.getMainLooper()).post {
            showResultData()
        }
    }

    fun showProgress() {
        Handler(Looper.getMainLooper()).post {
            progressBar.isVisible = true
        }
    }

    fun hideProgress() {
        Handler(Looper.getMainLooper()).post {
            progressBar.isVisible = false
        }
    }

    private fun bindViews() {
        makeResultButton.setOnClickListener {
            mainController.saveResult(
                first = firstEditText.text.toString(),
                second = secondEditText.text.toString()
            )
        }
    }

    private fun showResultData() {
        resultTextView.text = resultModel.getTotalResult()
    }
}
