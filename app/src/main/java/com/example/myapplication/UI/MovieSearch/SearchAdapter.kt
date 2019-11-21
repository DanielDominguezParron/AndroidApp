package com.example.myapplication.UI.MovieSearch


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Movie
import com.example.myapplication.R

class SearchAdapter(private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var movies = listOf<Movie>()

    fun addCities(newMovies: List<Movie>) {
        this.movies = newMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    class ViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
        private val movieTxt = view.findViewById<TextView>(R.id.cityTxt)

        fun bind(movie: Movie, listener: (Movie) -> Unit) {
            movieTxt.text = movie.title
            this.itemView.setOnClickListener { listener.invoke(movie) }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
                return ViewHolder(view)
            }
        }
    }

}