package com.ahmedtawfik.moviesapp.model.entity

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity(tableName = "movie_table")
data class Movie(
    var adult: Boolean,
    var backdrop_path: String?,
    var genre_ids: List<Integer>,
    @PrimaryKey
    var id: Int,
    var original_language: String,
    var original_title: String,
    var overview: String?,
    var popularity: Float,
    var poster_path: String?,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Float,
    var vote_count: Int
) : Parcelable