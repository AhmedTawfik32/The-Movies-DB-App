package com.ahmedtawfik.moviesapp.model.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmedtawfik.moviesapp.model.entity.Movie
import com.ahmedtawfik.moviesapp.model.local.roomdb.typeconverters.IntegerListConverter

/***
 * @author Ahmed Tawfik
 *
 * Room database
 */

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(value = [IntegerListConverter::class])
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
}