package com.terry.common.util

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager

/*
 * Created by Taehyung Kim on 2021-07-18
 */
class KeyboardUtil private constructor(
    context: Context
) {

    private val imm: InputMethodManager =
        context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

    fun showKeyboard(target: View) {
        imm.showSoftInput(target, 0)
    }

    fun hideKeyboard(target: View) {
        imm.hideSoftInputFromWindow(target.windowToken, 0)
    }

    companion object {
        private var INSTANCE: KeyboardUtil? = null

        fun getInstance(context: Context): KeyboardUtil =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: KeyboardUtil(context).also { INSTANCE = it }
            }
    }
}