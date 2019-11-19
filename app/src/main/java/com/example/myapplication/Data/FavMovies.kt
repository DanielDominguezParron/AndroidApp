
package com.example.myapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavMovies (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text :String
)
