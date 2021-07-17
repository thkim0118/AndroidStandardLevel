package com.terry.books.di

import android.app.Activity
import com.terry.books.BooksMainActivity
import com.terry.common.di.CoreModuleDependencies
import dagger.BindsInstance
import dagger.Component

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@Component(
    dependencies = [CoreModuleDependencies::class],
    modules = [BooksModule::class]
)
interface BooksComponent {

    fun inject(booksMainActivity: BooksMainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            dependentModule: CoreModuleDependencies,
            @BindsInstance activity: Activity
        ): BooksComponent
    }
}