package com.example.myapplication.UI.Favorite

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.Local.DatabaseFactory
import com.example.myapplication.Data.Local.FavMovies
import com.example.myapplication.Data.Local.FavMoviesDao
import com.example.myapplication.R
import android.view.MenuInflater
import com.example.myapplication.Data.Local.DBLocalRepository
import com.example.myapplication.UI.MovieDetails.MovieDetailsActivity


class favoriteFragment : Fragment(), FavoritesView {


    private lateinit var favoriteAdapter: FavoritesAdapter
    private lateinit var moviesRecycler: RecyclerView
    private lateinit var presenter: FavoritesPresenter
    private lateinit var favoritedao: FavMoviesDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        val database = DatabaseFactory.get(this.context!!)
        favoritedao = database.favoriteDao()
        val localRepository =
            DBLocalRepository(
                favoritedao
            )
        presenter = FavoritesPresenter(this, localRepository)
        moviesRecycler = view.findViewById(R.id.favoriteRecyclerView)
        moviesRecycler.layoutManager = LinearLayoutManager(this.context)
        moviesRecycler.setHasFixedSize(true)
        favoriteAdapter = FavoritesAdapter {
            presenter.movieClicked(it)
        }
        moviesRecycler.adapter = favoriteAdapter
        presenter.updateListMovies()
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.updateListMovies()
    }

    override fun openMovieDetail(id: Int) {
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
            presenter.dropMovies()
            presenter.updateListMovies()
            true
        }
        R.id.orderName -> {
            presenter.orderMovies()
            true
        }
        R.id.orderYear -> {


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



