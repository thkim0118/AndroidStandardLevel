package com.terry.books

import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.terry.books.adapter.BookAdapter
import com.terry.books.adapter.BookSearchHistoryAdapter
import com.terry.books.api.BookService
import com.terry.books.databinding.ActivityBooksMainBinding
import com.terry.books.di.DaggerBooksComponent
import com.terry.books.model.BestSellerDTO
import com.terry.books.model.SearchBookDTO
import com.terry.books.viewmodel.BooksViewModel
import com.terry.common.LogT
import com.terry.common.base.BaseActivity
import com.terry.common.di.CoreModuleDependencies
import com.terry.local.model.BookSearchHistory
import dagger.hilt.android.EntryPointAccessors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class BooksMainActivity :
    BaseActivity<ActivityBooksMainBinding>(ActivityBooksMainBinding::inflate) {

    private lateinit var adapter: BookAdapter
    private lateinit var bookSearchHistoryAdapter: BookSearchHistoryAdapter

    private lateinit var bookService: BookService

    private var keywords: MutableList<BookSearchHistory> = mutableListOf()

    @Inject
    lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)

        initBookRecyclerView()
        initSearchHistoryRecyclerView()
        initSearchEditText()
        getSearchHistoryData()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks(BuildConfig.interpark_key)
            .enqueue(object : Callback<BestSellerDTO> {
                override fun onResponse(
                    call: Call<BestSellerDTO>,
                    response: Response<BestSellerDTO>
                ) {
                    if (response.isSuccessful.not()) {
                        LogT.e("is not successful")
                        return
                    }

                    LogT.d(response.body()?.books.toString())
                    adapter.submitList(response.body()?.books.orEmpty())
                }

                override fun onFailure(call: Call<BestSellerDTO>, t: Throwable) {
                    LogT.e(t.stackTraceToString())
                }

            })
    }

    private fun initCoreDependentInjection() {
        val coreDependencies = EntryPointAccessors.fromApplication(
            applicationContext,
            CoreModuleDependencies::class.java
        )

        DaggerBooksComponent.factory().create(
            dependentModule = coreDependencies,
            activity = this
        ).inject(this)
    }

    private fun search(keyword: String) {
        bookService.getBooksByName(BuildConfig.interpark_key, keyword)
            .enqueue(object : Callback<SearchBookDTO> {
                override fun onResponse(
                    call: Call<SearchBookDTO>,
                    response: Response<SearchBookDTO>
                ) {
                    hideHistoryView()

                    viewModel.saveSearchKeyword(keyword)

                    if (response.isSuccessful.not()) {
                        LogT.e("is not successful")
                        return
                    }

                    adapter.submitList(response.body()?.books.orEmpty())
                }

                override fun onFailure(call: Call<SearchBookDTO>, t: Throwable) {
                    hideHistoryView()

                    LogT.e(t.stackTraceToString())
                }

            })
    }

    private fun initBookRecyclerView() {
        adapter = BookAdapter()

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

            runOnUiThread {
                bookSearchHistoryAdapter.submitList(keywords.reversed().orEmpty())
            }
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