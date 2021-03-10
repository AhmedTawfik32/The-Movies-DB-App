package com.ahmedtawfik.moviesapp.model.entity

import androidx.annotation.Keep

@Keep
data class GuestSession(
    var success: Boolean,
    var guest_session_id: String,
    var expires_at: String
)