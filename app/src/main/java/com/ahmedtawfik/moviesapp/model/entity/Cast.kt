package com.ahmedtawfik.moviesapp.model.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Cast(
    var adult: Boolean,
    var gender: Int?,
    var id: Int,
    var known_for_department: String,
    var name: String,
    var original_name: String,
    var popularity: Float,
    var profile_path: String?,
    var cast_id: Int,
    var character: String,
    var credit_id: String,
    var order: Int

) : Parcelable