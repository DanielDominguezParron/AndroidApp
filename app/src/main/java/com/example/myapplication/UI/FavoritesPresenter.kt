package com.example.myapplication.UI

import com.example.myapplication.Data.FavMovies
import com.example.myapplication.Data.FavMoviesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesPresenter(val view: FavoritesView) {
    fun updateListAdress(favoritedao: FavMoviesDao) {

        CoroutineScope(Dispatchers.IO).launch {
            val name = favoritedao.getAll()
            view.showFavorites(name)

        }
    }
}

interface FavoritesView {
    fun showFavorites(favorite: List<FavMovies>)
}