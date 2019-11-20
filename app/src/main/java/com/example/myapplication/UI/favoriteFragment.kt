package com.example.myapplication.UI

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.DatabaseFactory
import com.example.myapplication.Data.FavMovies
import com.example.myapplication.Data.FavMoviesDao


import com.example.myapplication.R
import android.view.MenuInflater


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
        favoriteAdapter = FavoritesAdapter(
            presenter.cityClicked(it = id)
        )
        //while (favoriteAdapter.notifyDataSetChanged().equals("true"))
        //presenter.updateListMovies(favoritedao)

        moviesRecycler.adapter = favoriteAdapter

        val database = DatabaseFactory.get(this.context!!)
        favoritedao = database.favoriteDao()
        presenter.updateListMovies(favoritedao)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.updateListMovies(favoritedao)
    }

    override fun openCityDetail(id: Int) {
        val intent = Intent(this.context, MovieDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fav_options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.deleteFavs -> {
            presenter.dropMovies(favoritedao)
            true
        }
        R.id.orderFavs -> {

            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun showFavorites(favorite: List<FavMovies>) {
        favoriteAdapter.addMovie(favorite)
    }
}



