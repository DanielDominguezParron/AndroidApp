package com.example.myapplication.Data.Local

interface LocalRepository {
    suspend fun updateListMovies(): List<FavMovies>
    suspend fun deleteByUserId(userId: Int)
    suspend fun insert(movies: FavMovies)
    suspend fun orderMovies()
    suspend fun dropMovies()
}