package com.terry.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.terry.local.model.Review
import kotlinx.coroutines.flow.Flow

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@Dao
interface ReviewDao {

    @Query("SELECT * FROM review WHERE id == :id")
    fun getOneReview(id: Int): Flow<Review>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveReview(review: Review)
}