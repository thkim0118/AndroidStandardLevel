package com.terry.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * Created by Taehyung Kim on 2021-07-17
 */
@Entity
data class BookSearchHistory(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "keyword") val keyword: String?
)
