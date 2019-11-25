package com.example.myapplication.Data.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavMoviesDao {
    @Query("SELECT * FROM FavMovies Order By :name")
    suspend fun getAll(name: String = "title"): List<FavMovies>

    @Query("DELETE FROM FavMovies WHERE id = :userId")
    suspend fun deleteByUserId(userId: Int)

    @Query("DELETE FROM FavMovies")
    fun deleteAll()

    @Insert
    suspend fun insert(movies: FavMovies)
}