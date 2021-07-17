package com.terry.books.di

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.terry.books.viewmodel.BookDetailViewModel
import com.terry.books.viewmodel.BooksDetailViewModelProvider
import com.terry.books.viewmodel.BooksViewModel
import com.terry.books.viewmodel.BooksViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@InstallIn(ActivityComponent::class)
@Module
class BooksModule {

    @Provides
    fun provideBooksViewModel(activity: Activity, factory: BooksViewModelProvider) =
        ViewModelProvider(
            activity as ViewModelStoreOwner,
            factory
        ).get(BooksViewModel::class.java)

    @Provides
    fun provideBookDetailViewModel(activity: Activity, factory: BooksDetailViewModelProvider) =
        ViewModelProvider(
            activity as ViewModelStoreOwner,
            factory
        ).get(BookDetailViewModel::class.java)

}