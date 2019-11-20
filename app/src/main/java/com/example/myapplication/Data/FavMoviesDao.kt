package com.example.myapplication.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavMoviesDao {
    @Query("SELECT * FROM FavMovies")
    suspend fun getAll(): List<FavMovies>
    
    @Query("DELETE FROM FavMovies")
    fun deleteAll()

    @Insert
    suspend fun insert(movies: FavMovies)
}