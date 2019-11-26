package com.example.myapplication.UI.MovieSearch

import com.example.myapplication.Data.Remote.RemoteRepository
import com.example.myapplication.Model.DetailMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPresenter(
    val view: MovieSearchView,
    val remoteRepository: RemoteRepository
) {

    fun searchClicked(searchTerm: String) {
        if (searchTerm.isEmpty()) return
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val movies = remoteRepository.searchMovies(searchTerm)
                withContext(Dispatchers.Main) {
                    if (movies.isEmpty()) {
                        view.showEmpty()
                        return@withContext
                    }
                    view.showCities(movies)
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