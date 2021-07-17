package com.terry.books.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.terry.books.databinding.ItemBookSearchHistoryBinding
import com.terry.local.model.BookSearchHistory

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class BookSearchHistoryAdapter(
    val historyDeleteListener: (String) -> Unit,
    val itemClickedListener: (String) -> Unit
) : ListAdapter<BookSearchHistory, BookSearchHistoryAdapter.BookSearchHistoryItemViewHolder>(
    diffUtil
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookSearchHistoryItemViewHolder {
        return BookSearchHistoryItemViewHolder(
            ItemBookSearchHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            historyDeleteListener,
            itemClickedListener
        )
    }

    override fun onBindViewHolder(holder: BookSearchHistoryItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class BookSearchHistoryItemViewHolder(
        private val binding: ItemBookSearchHistoryBinding,
        private val historyDeleteListener: (String) -> Unit,
        private val itemClickedListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(searchHistory: BookSearchHistory) {
            binding.historyKeywordTextView.text = searchHistory.keyword

            binding.historyKeywordDeleteButton.setOnClickListener {
                historyDeleteListener(searchHistory.keyword.orEmpty())
            }

            binding.historyKeywordTextView.setOnClickListener {
                itemClickedListener(searchHistory.keyword.orEmpty())
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BookSearchHistory>() {
            override fun areItemsTheSame(
                oldItem: BookSearchHistory,
                newItem: BookSearchHistory
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: BookSearchHistory,
                newItem: BookSearchHistory
            ): Boolean {
                return oldItem.keyword == newItem.keyword
            }

        }
    }
}