package com.terry.location.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
 * Created by Taehyung Kim on 2021-08-01
 */
@Parcelize
data class SearchResultEntity(
    val fullAddress: String,
    val name: String,
    val locationLatLng: LocationLatLngEntity
) : Parcelable
