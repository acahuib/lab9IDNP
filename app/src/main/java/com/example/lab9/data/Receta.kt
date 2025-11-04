package com.example.lab9.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recetas")
data class Receta(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val ingredientes: String,
    val pasos: String
)