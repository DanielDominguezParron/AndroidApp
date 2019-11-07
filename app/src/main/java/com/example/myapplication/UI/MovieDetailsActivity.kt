package com.example.myapplication.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.Data.RetrofitFactory
import com.example.myapplication.Model.*
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        var idWeather = intent.extras?.get("id")

        val weatherApi = RetrofitFactory.getMovieApi()
        CoroutineScope(Dispatchers.IO).launch {
            val response = weatherApi.searchMovieDetails(
                idWeather as Int,
                "42a33cb748549aa2038e2048e51e01b2"
            )
            Log.e("tag", response.toString())


            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val responseJSON = response.body()!!
                    details(responseJSON)
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            val response = weatherApi.searchCredits(
                idWeather as Int,
                "42a33cb748549aa2038e2048e51e01b2"
            )
            Log.e("tag", response.toString())


            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val responseCredits = response.body()!!
                    cast(responseCredits)
                }
            }
        }
    }

    fun details(el: (DetailMovie)) {
        Title.text = el.original_title
        YearContent.text = el.release_date
        Description.text = el.overview
        genre(el.genres.map { it.name })
        Rating.text = el.vote_average.toString()
        val photo = "https://image.tmdb.org/t/p/w500" + el.backdrop_path
        Log.e("tag", photo)
        Picasso.get().load(photo).placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground).into(backdrop_path)
    }

    fun genre(elto: List<String>) {
        GenreContent.text = elto.toString()
    }


    fun crew(actorDirector: (crew)) {
        val rol =
            actorDirector.crew.filter { it.job == "Director" }.map { it.name }.joinToString { "," }
        directorContent.text = rol

    }


    fun cast(actorList: (cast)) {
        val actor1 = actorList.cast.component1()

        detcast(actor1)

    }

    fun detcast(actorName: (detailCast)) {
        if (actorName.name.isNotEmpty())
            actorContent.text = actorName.name

    }
}
