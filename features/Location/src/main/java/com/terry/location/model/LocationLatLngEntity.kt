package com.terry.location.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
 * Created by Taehyung Kim on 2021-08-01
 */
@Parcelize
data class LocationLatLngEntity(
    val latitude: Float,
    val longitude: Float
) : Parcelable
