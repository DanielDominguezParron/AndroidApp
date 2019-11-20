package com.example.myapplication.UI

import com.example.myapplication.Data.FavMovies
import com.example.myapplication.Data.FavMoviesDao
import com.example.myapplication.Model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesPresenter(val view: FavoritesView) {
    fun updateListMovies(favoritedao: FavMoviesDao) {

        CoroutineScope(Dispatchers.IO).launch {
            val name = favoritedao.getAll()
            view.showFavorites(name)

        }
    }

    fun dropMovies(favoritedao: FavMoviesDao) {

        CoroutineScope(Dispatchers.IO).launch {
            favoritedao.deleteAll()
        }
    }

    fun cityClicked(it: FavMovies) {
        view.openCityDetail(it.id)
    }

}

interface FavoritesView {
    fun showFavorites(favorite: List<FavMovies>)
    fun openCityDetail(id: Int)
}