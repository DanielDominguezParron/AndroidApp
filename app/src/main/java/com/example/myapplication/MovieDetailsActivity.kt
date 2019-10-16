package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.myapplication.Model.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlin.math.log

class MovieDetailsActivity : AppCompatActivity() {
    val movie = Movie(
        id = 1,
        title = "Pulp Fiction",
        year = "1996",
        genre = "Thriller",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel odio metus. Mauris pellentesque augue est. Donec orci dui, congue vitae tortor nec, posuere euismod velit. Sed nec orci consequat, facilisis tortor et, vehicula lacus. Etiam ultrices eros urna, et sodales arcu sollicitudin quis. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris facilisis dapibus enim vitae scelerisque. Nam aliquet pulvinar arcu nec vestibulum. Mauris quis tellus porta, condimentum dolor eget, aliquam libero.",
        rating = 8.9
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        with(movie) {
            MovieTitle.text = title
            Year.text = year
            Genre.text = genre
            Description.text = description
            Rating.text = rating.toString()
        }
    }


    override fun onStart() {
        super.onStart()
        Log.e("MovieDetails","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("MovieDetails","onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.e("MovieDetails","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MovieDetails","onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e("MovieDetails","onDestroy")
    }
}
