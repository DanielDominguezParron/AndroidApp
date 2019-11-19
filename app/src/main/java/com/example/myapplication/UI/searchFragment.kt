package com.example.myapplication.UI

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
import com.example.myapplication.Model.Movie
import com.example.myapplication.R


class searchFragment : Fragment(), MovieSearchView {


    private lateinit var moviesRecycler: RecyclerView
    private lateinit var moviesAdapter: MoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val movieSearch = view.findViewById<SearchView>(R.id.searchView)
        moviesRecycler = view.findViewById(R.id.moviesRecyclerView)
        moviesRecycler.layoutManager = LinearLayoutManager(this.context)
        moviesRecycler.setHasFixedSize(true)
        val presenter = SearchPresenter(this)
        moviesAdapter = MoviesAdapter {
            presenter.cityClicked(it)
        }
        moviesRecycler.adapter = moviesAdapter
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
        Toast.makeText(this.context, "Error fetching cities", Toast.LENGTH_SHORT).show()
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

    override fun showCities(movies: List<Movie>) {
        moviesAdapter.addCities(movies)

        moviesRecycler.visibility = View.VISIBLE
        // emptyView.visibility = View.GONE
    }
}