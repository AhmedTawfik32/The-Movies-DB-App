package com.ahmedtawfik.moviesapp.model.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class MovieOverview(
    var adult: Boolean,
    var backdrop_path: String?,
    var belongs_to_collection: String?,
    var budget: Int,
    var genres: List<Genre>,
    var homepage: String?,
    var id: Int,
    var imdb_id: String?,
    var original_language: String,
    var original_title: String,
    var overview: String?,
    var popularity: Float,
    var poster_path: String?,
    var production_companies: List<ProductionCompany>,
    var production_countries: List<ProductionCountry>,
    var release_date: String,
    var revenue: Int,
    var runtime: Int?,
    var spoken_languages: List<SpokenLanguage>,
    var status: String,
    var tagline: String?,
    var title: String,
    var video: Boolean,
    var vote_average: Float,
    var vote_count: Int
) : Parcelable