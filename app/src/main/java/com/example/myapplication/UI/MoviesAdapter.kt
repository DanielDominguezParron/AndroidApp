package com.example.myapplication.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Model.Movie
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_movie_details.view.YearContent
import kotlinx.android.synthetic.main.string_item.view.*

import kotlin.collections.ArrayList

class MoviesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.string_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is MovieViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(movieList: List<Movie>) {
        items = movieList
    }

    class MovieViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val movieTitle = itemView.Title
        val movieOriginal = itemView.OriginalTitle
        val movieYear = itemView.YearContent
        val movieRating = itemView.Rating
        val movieImage: ImageView = itemView.ImageMovie

        fun bind(movie: Movie) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(movie.image)
                .into(movieImage)
            movieOriginal.text = movie.OriginalTitle
            movieTitle.text = movie.title
            movieYear.text = movie.year
            movieRating.text = movie.rating.toString()

        }

    }

}