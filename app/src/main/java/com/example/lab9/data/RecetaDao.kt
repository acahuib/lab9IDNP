package com.example.lab9.data

import androidx.room.*

@Dao
interface RecetaDao {
    @Insert
    suspend fun insertar(receta: Receta)

    @Query("SELECT * FROM recetas")
    suspend fun obtenerTodas(): List<Receta>

    @Delete
    suspend fun eliminar(receta: Receta)
}