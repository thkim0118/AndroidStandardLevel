package com.terry.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/*
 * Created by Taehyung Kim on 2021-06-26
 */
abstract class BaseActivity<B : ViewBinding>(
    private val inflate: ActivityInflater<B>
) : AppCompatActivity() {

    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate.invoke(layoutInflater)

        setContentView(binding.root)
    }
}