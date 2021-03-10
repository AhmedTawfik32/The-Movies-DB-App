package com.ahmedtawfik.moviesapp.model.entity

import androidx.annotation.Keep

@Keep
data class RatingResponse(
    var status_code: Int,
    var status_message: String
)