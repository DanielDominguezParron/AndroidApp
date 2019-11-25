package com.example.myapplication.UI.Favorite

import com.example.myapplication.Data.Local.FavMovies
import com.example.myapplication.Data.Local.LocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesPresenter(val view: FavoritesView, private val localRepository: LocalRepository) {
    fun updateListMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val name = localRepository.updateListMovies()
            withContext(Dispatchers.Main) {
                view.showFavorites(name)
            }
        }
    }

    fun orderMovies() {

        CoroutineScope(Dispatchers.IO).launch {
            localRepository.orderMovies()
        }
    }

    fun dropMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            localRepository.dropMovies()
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