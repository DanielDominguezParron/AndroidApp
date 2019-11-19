package com.example.myapplication.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.FavMovies
import com.example.myapplication.R

class FavoritesAdapter: RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private var movies = listOf<FavMovies>()

    fun addMovie(newMovies: List<FavMovies>) {
        this.movies = newMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class ViewHolder private constructor(view: View): RecyclerView.ViewHolder(view) {

        val myText: TextView = view.findViewById(R.id.nombre)


        fun bind(movies: FavMovies) {
            myText.text = movies.text
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.favs_items, parent, false)
                return ViewHolder(view)
            }
        }
    }

}
