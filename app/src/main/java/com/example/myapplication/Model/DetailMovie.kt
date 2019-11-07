package com.example.myapplication.Model

data class DetailMovie(
    val id: Int,
    val original_title: String,
    val release_date: String,
    val overview: String,
    val backdrop_path: String,
    val vote_average: Float,
    val genres: List<genresList>
)

data class genresList(val name: String)
data class cast(val cast: List<detailCast>)
data class crew(val crew: List<detailCrew>)
data class detailCast(val name: String)
data class detailCrew(val name: String, val job: String)