package com.example.myapplication.UI.MovieDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myapplication.Data.DatabaseFactory
import com.example.myapplication.Model.*
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(),
    MovieDetailsView {

    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        var idMovie = intent.extras?.getInt("id")
        val database = DatabaseFactory.get(this)
        val favoritedao = database.favoriteDao()
        val presenter = MovieDetailsPresenter(this)
        val api_key = "42a33cb748549aa2038e2048e51e01b2"
        presenter.movieDetails(idMovie, api_key)
        presenter.movieCast(idMovie, api_key)
        val favMoviesDao = favoritedao
        val favMovie = findViewById(R.id.favMovie) as ImageView
        favMovie.setOnClickListener {
            presenter.CheckDao(
                favMoviesDao,
                idMovie!!,
                name
            )
        }
    }

    override fun openDetails(el: (DetailMovie)) {
        name = el.original_title
        Title.text = el.original_title
        YearContent.text = el.release_date
        Description.text = el.overview
        genre(el.genres.map { it.name })
        Rating.text = el.vote_average.toString()
        val photo = "https://image.tmdb.org/t/p/w500" + el.backdrop_path
        Picasso.get().load(photo).placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground).into(backdrop_path)
    }

    fun genre(elto: List<String>) {
        GenreContent.text = elto.toString()
    }

    override fun crew(actorDirector: (Cast)) {
        val rol =
            actorDirector.crew.filter { it.job == "Director" }.map { it.name }.toString()
        directorContent.text = rol

    }


    override fun cast(actorList: (Cast)) {
        var hola = actorList.cast.map { it.name }

        detcast(hola)

    }

    override fun insertAlert() {
        Toast.makeText(
            applicationContext,
            "Has añadido la pelicula de tu lista de favoritos",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun deleteAlert() {
        Toast.makeText(
            applicationContext,
            "Has quitado la pelicula de tu lista de favoritos",
            Toast.LENGTH_SHORT
        ).show()
    }
    override fun yellowStar() {
        favMovie.setColorFilter(ContextCompat.getColor(this, R.color.design_default_color_primary), android.graphics.PorterDuff.Mode.MULTIPLY)
    }
    override fun clearColorFilter() {
        favMovie.clearColorFilter()
    }

    fun detcast(actorName: List<String>) {
        if (actorName.isNotEmpty()) {
            val a = actorName[0]
            val b = actorName[1]
            val c = actorName[2]
            val d = a.plus(",").plus(b).plus(",").plus(c)
            actorContent.text = d
        }
        else
            actorContent.text = "No hay actores registrados"
    }
}
