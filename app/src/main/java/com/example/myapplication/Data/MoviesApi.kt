package com.example.myapplication.Data



import com.example.myapplication.Model.DetailMovie
import com.example.myapplication.Model.MovieResults
import com.example.myapplication.Model.cast
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface MoviesApi {
    @GET("search/movie?api_key=42a33cb748549aa2038e2048e51e01b2")
    suspend fun searchMovies(@Query("query") text: String): Response<MovieResults>

    @GET("movie/{movie_id}?")
    suspend fun searchMovieDetails(@Path("movie_id") id: Int, @Query("api_key") api_key: String): Response<DetailMovie>

    @GET("movie/{movie_id}/credits")
    suspend fun searchCredits(@Path("movie_id") id: Int, @Query("api_key") api_key: String): Response<cast>
}


object RetrofitFactory {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    fun getMovieApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(MoviesApi::class.java)
    }
}