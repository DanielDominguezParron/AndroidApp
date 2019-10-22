package com.example.myapplication.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.Model.Movie
import com.example.myapplication.Model.MovieDetails
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    val movie = MovieDetails(
        id = 1,
        title = "Pulp Fiction",
        year = "1996",
        genreName = "Thriller",
        overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel odio metus. Mauris pellentesque augue est. Donec orci dui, congue vitae tortor nec, posuere euismod velit. Sed nec orci consequat, facilisis tortor et, vehicula lacus. Etiam ultrices eros urna, et sodales arcu sollicitudin quis. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris facilisis dapibus enim vitae scelerisque. Nam aliquet pulvinar arcu nec vestibulum. Mauris quis tellus porta, condimentum dolor eget, aliquam libero.",
        popularity = 8.9
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        with(movie) {

            Title.text = title
            Year.text = year
            GenreContent.text = genreName
            Description.text = overview
            Rating.text = popularity.toString()
            Picasso.get()
                .load("https://toppng.com/public/uploads/preview/resultado-de-imagen-de-saitama-saitama-one-punch-ma-11563016585efxo59slfk.png")
                .into(
                    ImageMovie
                )
        }
    }


    override fun onStart() {
        super.onStart()
        Log.e("MovieDetails", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MovieDetails", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MovieDetails", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MovieDetails", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MovieDetails", "onDestroy")
    }
}
