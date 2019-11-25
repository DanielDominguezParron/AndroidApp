package com.example.myapplication.Data.Remote

import com.example.myapplication.Model.Cast
import com.example.myapplication.Model.DetailMovie

interface RemoteRepository {
    suspend fun searchMovies(query: String): List<DetailMovie>
    suspend fun getMovieDetail(id: Int, api_key: String): DetailMovie
    suspend fun getCast(id: Int, api_key: String): Cast
}