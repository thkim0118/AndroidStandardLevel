package com.terry.books

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.terry.books.adapter.BookAdapter
import com.terry.books.adapter.BookSearchHistoryAdapter
import com.terry.books.databinding.ActivityBooksMainBinding
import com.terry.books.di.DaggerBooksComponent
import com.terry.books.viewmodel.BooksViewModel
import com.terry.common.LogT
import com.terry.common.base.BaseActivity
import com.terry.common.di.UseCaseDependencies
import com.terry.common.util.KeyboardUtil
import com.terry.local.model.BookSearchHistory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class BooksMainActivity :
    BaseActivity<ActivityBooksMainBinding>(ActivityBooksMainBinding::inflate) {

    private lateinit var adapter: BookAdapter
    private lateinit var bookSearchHistoryAdapter: BookSearchHistoryAdapter

    private var keywords: MutableList<BookSearchHistory> = mutableListOf()

    private val keyboardUtil: KeyboardUtil by lazy { KeyboardUtil.getInstance(this) }

    @Inject
    lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)

        initBookRecyclerView()
        initSearchHistoryRecyclerView()
        initSearchEditText()
        getSearchHistoryData()
        getBestSellerBooks()
        observeLiveData()
    }

    private fun initCoreDependentInjection() {
        val useCaseDependencies = EntryPointAccessors.fromApplication(
            applicationContext,
            UseCaseDependencies::class.java
        )

        DaggerBooksComponent.factory().create(
            dependentModule = useCaseDependencies,
            activity = this
        ).inject(this)
    }

    private fun observeLiveData() {
        observeBestSellerResponse()
        observeSearchBooksResponse()
    }

    private fun observeBestSellerResponse() {
        viewModel.bestSellerResponse.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun observeSearchBooksResponse() {
        viewModel.searchBookResponse.observe(this) {
            hideHistoryView()

            adapter.submitList(it)
        }
    }

    private fun getBestSellerBooks() {
        viewModel.getBestSellerBooks(BuildConfig.interpark_key)
    }

    // search history 에서 아이템을 선택하면 바로 search가 되는 기능
    private fun search(keyword: String) {
        viewModel.getBooksByName(BuildConfig.interpark_key, keyword)
    }

    private fun initBookRecyclerView() {
        adapter = BookAdapter(itemClickedListener = {
            val intent = Intent(this, BookDetailActivity::class.java).apply {
                putExtra("bookModel", it)
            }

            startActivity(intent)
        })

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    private fun initSearchHistoryRecyclerView() {
        bookSearchHistoryAdapter = BookSearchHistoryAdapter {
            viewModel.deleteSearchKeyword(it)
            showHistoryView()
        }

        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historyRecyclerView.adapter = bookSearchHistoryAdapter
    }

    private fun initSearchEditText() {
        binding.searchEditText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN) {
                search(binding.searchEditText.text.toString())
                keyboardUtil.hideKeyboard(binding.searchEditText)
                return@setOnKeyListener true
            }

            return@setOnKeyListener false
        }

        binding.searchEditText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                showHistoryView()
            }
            return@setOnTouchListener false
        }
    }

    private fun getSearchHistoryData() {
        viewModel.getAllBookSearchHistory().observe(this) {
            LogT.d("Trigger -> $it")
            keywords = it.toMutableList()

            bookSearchHistoryAdapter.submitList(keywords.reversed().orEmpty())
        }
    }

    private fun showHistoryView() {
        bookSearchHistoryAdapter.submitList(keywords.reversed().orEmpty())

        binding.historyRecyclerView.isVisible = true
    }

    private fun hideHistoryView() {
        binding.historyRecyclerView.isVisible = false
    }
}