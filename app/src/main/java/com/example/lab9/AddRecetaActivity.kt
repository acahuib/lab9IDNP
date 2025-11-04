package com.example.lab9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.lab9.R
import com.example.lab9.data.AppDatabase
import com.example.lab9.data.Receta
import kotlinx.android.synthetic.main.activity_add_receta.*
import kotlinx.coroutines.launch

class AddRecetaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_receta)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "recetas-db"
        ).build()

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val ingredientes = etIngredientes.text.toString()
            val pasos = etPasos.text.toString()

            lifecycleScope.launch {
                db.recetaDao().insertar(Receta(nombre = nombre, ingredientes = ingredientes, pasos = pasos))
                finish()
            }
        }
    }
}