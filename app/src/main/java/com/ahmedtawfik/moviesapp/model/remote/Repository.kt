package com.ahmedtawfik.moviesapp.model.remote

import com.ahmedtawfik.moviesapp.model.local.roomdb.DatabaseRepository
import com.ahmedtawfik.moviesapp.model.local.sharedpref.SharedPreferenceHelper
import com.ahmedtawfik.moviesapp.model.remote.network.RemoteRepository

interface Repository : RemoteRepository, SharedPreferenceHelper, DatabaseRepository