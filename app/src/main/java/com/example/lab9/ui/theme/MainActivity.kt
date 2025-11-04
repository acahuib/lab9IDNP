package com.example.lab9.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.lab9.R
import com.example.lab9.data.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "recetas-db"
        ).build()

        btnAgregar.setOnClickListener {
            startActivity(Intent(this, AddRecetaActivity::class.java))
        }

        lifecycleScope.launch {
            val lista = db.recetaDao().obtenerTodas()
            rvRecetas.layoutManager = LinearLayoutManager(this@MainActivity)
            rvRecetas.adapter = RecetaAdapter(lista)
        }
    }
}