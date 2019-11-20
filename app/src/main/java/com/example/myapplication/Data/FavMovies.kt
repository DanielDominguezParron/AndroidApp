
package com.example.myapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavMovies (
    val id: Int,
    val text :String
)
