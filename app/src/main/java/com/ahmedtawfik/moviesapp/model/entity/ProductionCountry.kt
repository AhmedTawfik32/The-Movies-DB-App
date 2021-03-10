package com.ahmedtawfik.moviesapp.model.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ProductionCountry(
    var iso_3166_1: String,
    var name: String
) : Parcelable