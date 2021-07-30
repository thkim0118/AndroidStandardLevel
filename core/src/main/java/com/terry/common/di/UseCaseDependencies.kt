package com.terry.common.di

import com.terry.repository.usecase.airbnb.GetHouseListUseCase
import com.terry.repository.usecase.books.*
import com.terry.repository.usecase.books.remote.GetBestSellerBooksUseCase
import com.terry.repository.usecase.books.remote.GetBooksByNameUseCase
import com.terry.repository.usecase.calculator.DeleteAllHistoryUseCase
import com.terry.repository.usecase.calculator.GetAllHistoryUseCase
import com.terry.repository.usecase.calculator.InsertHistoryUseCase
import com.terry.repository.usecase.musicplayer.GetMusicListUseCase
import com.terry.repository.usecase.videoplayer.GetVideoListUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface UseCaseDependencies {

    fun getHistoryAllUseCase(): GetAllHistoryUseCase

    fun insertHistoryUseCase(): InsertHistoryUseCase

    fun deleteAllHistoryUseCase(): DeleteAllHistoryUseCase

    fun getBookSearchHistoryAllUseCase(): GetAllSearchHistoryUseCase

    fun insertBookSearchHistoryUseCase(): InsertBookSearchHistoryUseCase

    fun deleteBookSearchHistoryUseCase(): DeleteBookSearchHistoryUseCase

    fun getOneReviewUseCase(): GetOneReviewUseCase

    fun saveReviewUseCase(): SaveReviewUseCase

    fun getBooksByName(): GetBooksByNameUseCase

    fun getBestSellerBooks(): GetBestSellerBooksUseCase

    fun getHouseListUseCase(): GetHouseListUseCase

    fun getVideoListUseCase(): GetVideoListUseCase

    fun getMusicListUseCase(): GetMusicListUseCase
}