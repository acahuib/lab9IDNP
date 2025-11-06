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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa la base de datos Room
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "recetas-db"
        ).build()

        // Acción al presionar el botón
        binding.btnAgregar.setOnClickListener {
            startActivity(Intent(this, AddRecetaActivity::class.java))
        }

        // Cargar la lista de recetas en el RecyclerView
        lifecycleScope.launch {
            val lista = db.recetaDao().obtenerTodas()
            binding.rvRecetas.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rvRecetas.adapter = RecetaAdapter(lista)
        }
    }
}