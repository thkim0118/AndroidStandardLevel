package com.terry.books.di

import android.app.Activity
import com.terry.books.BookDetailActivity
import com.terry.books.BooksMainActivity
import com.terry.common.di.UseCaseDependencies
import dagger.BindsInstance
import dagger.Component

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@Component(
    dependencies = [UseCaseDependencies::class],
    modules = [BooksModule::class]
)
interface BooksComponent {

    fun inject(booksMainActivity: BooksMainActivity)
    fun inject(bookDetailActivity: BookDetailActivity)

    @Component.Factory
    interface Factory {
        fun create(
            dependentModule: UseCaseDependencies,
            @BindsInstance activity: Activity
        ): BooksComponent
    }
}