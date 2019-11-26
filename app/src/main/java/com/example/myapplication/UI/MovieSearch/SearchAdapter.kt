package com.example.myapplication.UI.MovieSearch


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.DetailMovie
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class SearchAdapter(private val listener: (DetailMovie) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var movies = listOf<DetailMovie>()

    fun addCities(newMovies: List<DetailMovie>) {
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
        private val movieTxt = view.findViewById<TextView>(R.id.movieTxt)
        private val yearTxt = view.findViewById<TextView>(R.id.year)
        private val voteAverage = view.findViewById<TextView>(R.id.vote_average)
        private val Backdrop = view.findViewById<ImageView>(R.id.Backdrop)


        fun bind(movie: DetailMovie, listener: (DetailMovie) -> Unit) {
            movieTxt.text = movie.original_title
            yearTxt.text = movie.release_date
            voteAverage.text = movie.vote_average.toString()
            val photo = "https://image.tmdb.org/t/p/w500" + movie.backdrop_path
            Picasso.get().load(photo).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(Backdrop)

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