package com.example.myapplication.UI.MovieSearch

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.Remote.RemoteRepository
import com.example.myapplication.Data.Remote.RetrofitFactory
import com.example.myapplication.Data.Remote.RetrofitRemoteRepository
import com.example.myapplication.Model.DetailMovie
import com.example.myapplication.R
import com.example.myapplication.UI.MovieDetails.MovieDetailsActivity


class searchFragment : Fragment(), MovieSearchView {

    private lateinit var moviesRecycler: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val movieSearch = view.findViewById<SearchView>(R.id.searchView)
        val remoteRepository: RemoteRepository =
            RetrofitRemoteRepository(RetrofitFactory.getMovieApi())
        moviesRecycler = view.findViewById(R.id.moviesRecyclerView)
        moviesRecycler.layoutManager = LinearLayoutManager(this.context)
        moviesRecycler.setHasFixedSize(true)
        val presenter = SearchPresenter(this, remoteRepository)
        searchAdapter = SearchAdapter {
            presenter.movieClicked(it)
        }
        moviesRecycler.adapter = searchAdapter
        movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(text: String): Boolean {
                val searchTerm = text
                presenter.searchClicked(searchTerm)
                return false
            }
        })
        return view

    }

    override fun showError() {
        Toast.makeText(this.context, "Error fetching movies", Toast.LENGTH_SHORT).show()
    }

    override fun showEmpty() {
        //emptyView.visibility = View.VISIBLE
        moviesRecycler.visibility = View.GONE
    }


    override fun openCityDetail(id: Int) {
        val intent = Intent(this.context, MovieDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun showCities(movies: List<DetailMovie>) {
        searchAdapter.addCities(movies)

        moviesRecycler.visibility = View.VISIBLE
        // emptyView.visibility = View.GONE
    }
}