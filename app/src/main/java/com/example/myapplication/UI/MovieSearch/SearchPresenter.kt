package com.example.myapplication.UI.MovieSearch

import com.example.myapplication.Data.RetrofitFactory
import com.example.myapplication.Model.DetailMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(
    val view: MovieSearchView
) {

    fun searchClicked(searchTerm: String) {
        if (searchTerm.isEmpty()) return
        val movieApi = RetrofitFactory.getMovieApi()
        CoroutineScope(Dispatchers.IO).launch {
            val response = movieApi.searchMovies(searchTerm)
            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val movies = response.body()!!
                        if (movies.results.isEmpty()) {
                            view.showEmpty()
                            return@withContext
                        }
                        view.showCities(response.body()?.results!!)
                    } else
                        view.showError()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError()
                }
            }

        }
    }

    fun movieClicked(movie: DetailMovie) {
        view.openCityDetail(movie.id)
    }

}

interface MovieSearchView {
    fun showCities(movies: List<DetailMovie>)
    fun openCityDetail(id: Int)
    fun showError()
    fun showEmpty()
}