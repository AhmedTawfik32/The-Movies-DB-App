package com.ahmedtawfik.moviesapp.model.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UpcomingMoviesResponse(
    var dates: MDate,
    var page: Int,
    var results: List<Movie>,
    var total_results: Int,
    var total_pages: Int
) : Parcelable