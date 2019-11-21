package com.example.myapplication.UI

import com.example.myapplication.Data.FavMovies
import com.example.myapplication.Data.FavMoviesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesPresenter(val view: FavoritesView) {
    fun updateListMovies(favoritedao: FavMoviesDao) {

        CoroutineScope(Dispatchers.IO).launch {
            val name = favoritedao.getAll()
            withContext(Dispatchers.Main) {
                view.showFavorites(name)
            }
        }
    }


    fun orderMovies(favoritedao: FavMoviesDao) {

        CoroutineScope(Dispatchers.IO).launch {
            favoritedao.getAll()
        }
    }

    fun dropMovies(favoritedao: FavMoviesDao) {

        CoroutineScope(Dispatchers.IO).launch {
            favoritedao.deleteAll()
        }
    }

    fun movieClicked(it: FavMovies) {
        view.openMovieDetail(it.id)
    }

}

interface FavoritesView {
    fun showFavorites(favorite: List<FavMovies>)
    fun openMovieDetail(id: Int)
}