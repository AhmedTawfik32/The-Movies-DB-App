package com.ahmedtawfik.moviesapp.model.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ProductionCompany(
    var name: String,
    var id: Int,
    var logo_path: String?,
    var origin_country: String
) : Parcelable