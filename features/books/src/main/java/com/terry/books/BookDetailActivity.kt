package com.terry.books

import android.os.Bundle
import com.bumptech.glide.Glide
import com.terry.books.databinding.ActivityDetailBinding
import com.terry.books.di.DaggerBooksComponent
import com.terry.books.viewmodel.BookDetailViewModel
import com.terry.common.base.BaseActivity
import com.terry.common.di.UseCaseDependencies
import com.terry.local.model.Review
import com.terry.remote.model.Book
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

/*
 * Created by Taehyung Kim on 2021-07-17
 */
class BookDetailActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    @Inject
    lateinit var viewModel: BookDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initCoreDependentInjection()
        super.onCreate(savedInstanceState)

        val model = intent.getParcelableExtra<Book>("bookModel")

        binding.titleTextView.text = model?.title.orEmpty()
        binding.descriptionTextView.text = model?.description.orEmpty()

        Glide
            .with(binding.coverImageView.context)
            .load(model?.coverSmallUrl.orEmpty())
            .into(binding.coverImageView)

        initSavedReviewData(model?.id?.toInt() ?: 0)

        binding.saveButton.setOnClickListener {
            viewModel.saveReview(
                Review(
                    model?.id?.toInt() ?: 0,
                    binding.reviewEditText.text.toString()
                )
            )
        }

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

    private fun initSavedReviewData(id: Int) {
        viewModel.getOneReview(id).observe(this) { item ->
            item?.review?.let { review ->
                binding.reviewEditText.setText(review)
            }
        }
    }
}
