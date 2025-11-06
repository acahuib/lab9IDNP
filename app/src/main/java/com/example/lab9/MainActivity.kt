package com.example.lab9

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.lab9.data.AppDatabase
import com.example.lab9.databinding.ActivityMainBinding
import com.example.lab9.ui.RecetaAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa la base de datos Room (solo una vez)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "recetas-db"
        ).build()

        // Configura el RecyclerView
        binding.rvRecetas.layoutManager = LinearLayoutManager(this)

        // Acción al presionar el botón de agregar receta
        binding.btnAgregar.setOnClickListener {
            val intent = Intent(this, AddRecetaActivity::class.java)
            startActivity(intent)
        }

        // Cargar recetas al iniciar
        cargarRecetas()
    }

    private fun cargarRecetas() {
        lifecycleScope.launch {
            val lista = db.recetaDao().obtenerTodas()
            binding.rvRecetas.adapter = RecetaAdapter(lista)
        }
    }

    override fun onResume() {
        super.onResume()
        cargarRecetas()
    }
}
