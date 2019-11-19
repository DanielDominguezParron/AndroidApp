package com.example.myapplication.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.DatabaseFactory
import com.example.myapplication.Data.FavMovies
import com.example.myapplication.Data.FavMoviesDao


import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class favoriteFragment : Fragment(), FavoritesView {
    private lateinit var favoriteAdapter: FavoritesAdapter
    private lateinit var moviesRecycler: RecyclerView
    private lateinit var presenter: FavoritesPresenter
    private lateinit var favoritedao: FavMoviesDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        presenter = FavoritesPresenter(this)
        moviesRecycler = view.findViewById(R.id.favoriteRecyclerView)
        moviesRecycler.layoutManager = LinearLayoutManager(this.context)
        moviesRecycler.setHasFixedSize(true)
        favoriteAdapter = FavoritesAdapter()
        moviesRecycler.adapter = favoriteAdapter

        val database = DatabaseFactory.get(this.context!!)
        favoritedao = database.favoriteDao()
        presenter.updateListAdress(favoritedao)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.updateListAdress(favoritedao)
    }

    override fun showFavorites(favorite: List<FavMovies>) {

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                favoriteAdapter.addMovie(favorite)
            }
        }
    }
}



