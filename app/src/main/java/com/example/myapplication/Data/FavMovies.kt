package com.example.myapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavMovies(
    @PrimaryKey val id: Int,
    val title: String
)
