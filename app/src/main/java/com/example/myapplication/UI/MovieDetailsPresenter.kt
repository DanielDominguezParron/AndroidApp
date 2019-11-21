package com.example.myapplication.UI

import android.util.Log
import com.example.myapplication.Data.FavMoviesDao
import com.example.myapplication.Data.RetrofitFactory
import com.example.myapplication.Model.DetailMovie
import com.example.myapplication.Model.Movie
import com.example.myapplication.Model.cast
import com.example.myapplication.Model.crew
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
                    //view.crew(responseJSON)
                }
            }
        }
    }

    fun CheckDao(favoritedao: FavMoviesDao, movieName: DetailMovie) {
        CoroutineScope(Dispatchers.IO).launch {
            val all = favoritedao.getAll()
            all.filter { it.text.equals(movieName.original_title) }
        }
    }

}

interface MovieDetailsView {
    fun openDetails(el: (DetailMovie))
    fun cast(actorList: (cast))
    fun crew(actorDirector: (crew))
}