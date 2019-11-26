package com.example.myapplication.Data.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavMovies(
    @PrimaryKey val id: Int,
    val original_title: String
)
