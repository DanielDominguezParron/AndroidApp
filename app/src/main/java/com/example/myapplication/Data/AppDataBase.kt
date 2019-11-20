package com.example.myapplication.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavMovies::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavMoviesDao
}

object DatabaseFactory {
    fun get(context: Context): AppDataBase {
        return Room
            .databaseBuilder(context, AppDataBase::class.java, "fav_database")
            .build()
    }
}