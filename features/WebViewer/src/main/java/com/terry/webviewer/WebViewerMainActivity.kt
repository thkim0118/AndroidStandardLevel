package com.terry.webviewer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import com.terry.common.base.BaseActivity
import com.terry.webviewer.databinding.ActivityWebViewerMainBinding

class WebViewerMainActivity :
    BaseActivity<ActivityWebViewerMainBinding>(ActivityWebViewerMainBinding::inflate) {

    private val goHomeButton by lazy {
        binding.goHomeButton
    }

    private val goBackButton by lazy {
        binding.goBackButton
    }

    private val goForwardButton by lazy {
        binding.goForwardButton
    }

    private val webView by lazy {
        binding.webView
    }

    private val addressBar by lazy {
        binding.addressBar
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

        bindViews()
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews() {
        // 지원하지 않는 uri 의 경우 default web app 으로 이동하므로 WebViewClient 를
        // override 하여 앱 내에서 실행하도록 변경
        webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true // 보안 이슈가 발생할 수 있다.
            loadUrl(DEFAULT_URL)
        }
    }

    private fun bindViews() {
        addressBar.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                webView.loadUrl(v.text.toString())
            }

            return@setOnEditorActionListener false
        }

        goBackButton.setOnClickListener {
            webView.goBack()
        }

        goForwardButton.setOnClickListener {
            webView.goForward()
        }

        goHomeButton.setOnClickListener {
            webView.loadUrl(DEFAULT_URL)
        }
    }

    companion object {
        private const val DEFAULT_URL = "http://www.google.com"
    }
}