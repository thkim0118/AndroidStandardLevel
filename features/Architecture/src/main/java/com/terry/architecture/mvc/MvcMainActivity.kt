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
import com.terry.architecture.mvc.model.MvcModel

class MvcMainActivity : AppCompatActivity() {

    private val resultModel = MvcModel()

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

        bindViews()
    }

    private fun bindViews() {
        // User Action
        makeResultButton.setOnClickListener {
            showProgress()

            // Controller : action 에 대한 처리 진행(Update UI)
            resultModel.saveResultData(
                first = firstEditText.text.toString(),
                second = secondEditText.text.toString()
            )

            // Update UI
            resultModel.saveSuccess = { isSuccess ->
                if (isSuccess) {
                    showResultData()
                }

                hideProgress()
            }
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

    private fun showResultData() {
        Handler(Looper.getMainLooper()).post {
            // Model Changed
            resultTextView.text = resultModel.getTotalResult()
        }
    }
}
