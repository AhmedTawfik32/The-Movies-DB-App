package com.ahmedtawfik.moviesapp.model.local.roomdb.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/***
 * @author Eng. Ahmed Tawfik
 *
 * TypeConverter is used to convert list of integers to and from a String class that Room can persist.
 */
class IntegerListConverter {

    @TypeConverter
    fun toString(values: List<Int>): String {
        var gson = Gson()
        return gson.toJson(values)
    }

    @TypeConverter
    fun fromString(value: String): List<Int> {
        var list = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson<List<Int>>(value, list)
    }
}