package com.example.myapplication.Data.Remote

import android.accounts.NetworkErrorException
import com.example.myapplication.Model.Cast
import com.example.myapplication.Model.DetailMovie

class RetrofitRemoteRepository(val moviesApi: MoviesApi) : RemoteRepository {
    override suspend fun getCast(id: Int, api_key: String): Cast {
        val response = moviesApi.searchCredits(id, api_key)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }
    }

    override suspend fun searchMovies(query: String): List<DetailMovie> {
        val response = moviesApi.searchMovies(query)
        if (response.isSuccessful) {
            return response.body()?.results!!
        } else {
            throw NetworkErrorException()
        }
    }

    override suspend fun getMovieDetail(id: Int, api_key: String): DetailMovie {
        val response = moviesApi.searchMovieDetails(id, api_key)
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }
    }
}
