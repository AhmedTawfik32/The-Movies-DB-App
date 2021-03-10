package com.ahmedtawfik.moviesapp.model.local.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ahmedtawfik.moviesapp.model.entity.Movie

/***
 * @author Ahmed Tawfik
 *
 * Room database access object
 *
 *DAO to apply database CRUD operation
 */
@Dao
interface MovieDAO {

    @Insert
    suspend fun addMovie(movie: Movie)

    @Query("select * from movie_table")
    suspend fun fetchAllMovies(): List<Movie>

    @Delete
    suspend fun deleteMovie(movie: Movie)

}