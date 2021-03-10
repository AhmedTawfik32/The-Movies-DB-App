package com.ahmedtawfik.moviesapp.model.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
class MDate(
    var maximum: String,
    var minimum: String
) : Parcelable
