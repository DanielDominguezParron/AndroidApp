package com.example.myapplication.Model

data class DetailMovie(
    val id: Int,
    val original_title: String,
    val release_date: String,
    val overview: String,
    val backdrop_path: String,
    val vote_average: Float,
    val genres: List<GenresList>
)

data class GenresList(val name: String)
data class Cast(val cast: List<DetailCast>)
data class Crew(val crew: List<DetailCrew>)
data class DetailCast(val name: String)
data class DetailCrew(val name: String, val job: String)