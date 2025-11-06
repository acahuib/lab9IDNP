package com.example.lab9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.lab9.data.AppDatabase
import com.example.lab9.data.Receta
import com.example.lab9.databinding.ActivityAddRecetaBinding
import kotlinx.coroutines.launch

class AddRecetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el binding
        binding = ActivityAddRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Base de datos Room
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "recetas-db"
        ).build()

        // Acción del botón guardar
        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val ingredientes = binding.etIngredientes.text.toString()
            val pasos = binding.etPasos.text.toString()

            lifecycleScope.launch {
                db.recetaDao().insertar(
                    Receta(
                        nombre = nombre,
                        ingredientes = ingredientes,
                        pasos = pasos
                    )
                )
                finish() // Regresa al MainActivity
            }
        }
    }
}
