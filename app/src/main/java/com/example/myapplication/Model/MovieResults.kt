package com.example.myapplication.Model

data class MovieResults(
    val results: List<Movie>

)

data class Movie(
    val id: Int,
    val title: String
)
