package com.example.myapplication.UI.MovieDetails

import com.example.myapplication.Data.Local.FavMovies
import com.example.myapplication.Data.Local.LocalRepository
import com.example.myapplication.Data.Remote.RemoteRepository
import com.example.myapplication.Model.DetailMovie
import com.example.myapplication.Model.Cast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsPresenter(
    val view: MovieDetailsView,
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) {

    fun movieDetails(idMovie: Int?, api_key: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val movie = remoteRepository.getMovieDetail(idMovie!!, api_key)
            withContext(Dispatchers.Main) {
                view.openDetails(movie)
            }
        }
    }

    fun movieCast(idMovie: Int?, api_key: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val movie = remoteRepository.getCast(idMovie!!, api_key)
            withContext(Dispatchers.Main) {
                view.cast(movie)
                view.crew(movie)
            }
        }
    }

    fun CheckDao(idMovie: Int, name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val allList = localRepository.updateListMovies()
            val nameMatched = allList.filter { it.title.equals(name) }
            if (nameMatched.isEmpty() || nameMatched.equals(null)) {
                localRepository.insert(
                    FavMovies(
                        id = idMovie,
                        title = name
                    )
                )

                withContext(Dispatchers.Main) {
                    view.insertAlert()
                    view.yellowStar()
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    localRepository.deleteByUserId(idMovie)
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