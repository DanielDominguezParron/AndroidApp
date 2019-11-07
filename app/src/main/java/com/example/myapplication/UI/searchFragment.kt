package com.example.myapplication.UI

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.RetrofitFactory
import com.example.myapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class searchFragment : Fragment() {
    private lateinit var movieName: String
    private lateinit var moviesRecycler: RecyclerView
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
        val moviesAdapter = MoviesAdapter {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }
        moviesRecycler.adapter = moviesAdapter
        movieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(text: String): Boolean {
                movieName = text
                val weatherApi = RetrofitFactory.getMovieApi()
                CoroutineScope(Dispatchers.IO).launch {
                    val response = weatherApi.searchMovies(movieName)
                    withContext(Dispatchers.Main) {
                        moviesAdapter.addCities(response.body()?.results!!)
                    }
                }
                return false
            }
        })
        return view
    }


}