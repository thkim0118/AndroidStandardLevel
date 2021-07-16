package com.terry.books

import android.os.Bundle
import com.terry.books.databinding.ActivityBooksMainBinding
import com.terry.common.base.BaseActivity

class BooksMainActivity :
    BaseActivity<ActivityBooksMainBinding>(ActivityBooksMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}