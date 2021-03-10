package com.ahmedtawfik.moviesapp.model.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Credit(
    var id: Int,
    var cast: List<Cast>,
    var crew: List<Crew>
) : Parcelable