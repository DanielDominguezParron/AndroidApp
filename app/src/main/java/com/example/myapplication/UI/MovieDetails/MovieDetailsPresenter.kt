package com.example.myapplication.UI.MovieDetails

import android.util.Log
import com.example.myapplication.Data.FavMovies
import com.example.myapplication.Data.FavMoviesDao
import com.example.myapplication.Data.RetrofitFactory
import com.example.myapplication.Model.DetailMovie
import com.example.myapplication.Model.Cast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsPresenter(
    val view: MovieDetailsView
) {

    fun movieDetails(idMovie: Any?, api_key: String) {
        val movieApi = RetrofitFactory.getMovieApi()
        CoroutineScope(Dispatchers.IO).launch {
            val response = movieApi.searchMovieDetails(
                idMovie as Int,
                api_key
            )
            Log.e("tag", response.toString())


            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val responseJSON = response.body()!!
                    view.openDetails(responseJSON)
                }
            }
        }
    }

    fun movieCast(idMovie: Any?, api_Key: String) {
        val movieApi = RetrofitFactory.getMovieApi()
        CoroutineScope(Dispatchers.IO).launch {
            val response = movieApi.searchCredits(
                idMovie as Int,
                api_Key
            )
            Log.e("tag", response.toString())


            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val responseJSON = response.body()!!
                    view.cast(responseJSON)
                    view.crew(responseJSON)
                }
            }
        }
    }

    fun CheckDao(favoritedao: FavMoviesDao, idMovie: Int, movieName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val allList = favoritedao.getAll()
            val nameMatched = allList.filter { it.title.equals(movieName) }
            if (nameMatched.isEmpty() || nameMatched.equals(null)) {
                CoroutineScope(Dispatchers.IO).launch {
                    favoritedao.insert(
                        FavMovies(
                            id = idMovie,
                            title = movieName
                        )
                    )
                }
                withContext(Dispatchers.Main) {
                    view.insertAlert()
                    view.yellowStar()
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    favoritedao.deleteByUserId(
                        idMovie
                    )
                }
                withContext(Dispatchers.Main) {
                    view.deleteAlert()
                    view.clearColorFilter()
                }

            }
        }
    }
}


interface MovieDetailsView {
    fun openDetails(el: (DetailMovie))
    fun cast(actorList: (Cast))
    fun crew(actorDirector: (Cast))
    fun deleteAlert()
    fun insertAlert()
    fun yellowStar()
    fun clearColorFilter()
}