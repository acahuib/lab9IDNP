package com.example.lab9.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Receta::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recetaDao(): RecetaDao
}