package com.example.lab9.ui.theme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.R
import com.example.lab9.data.Receta

class RecetaAdapter(private val lista: List<Receta>) :
    RecyclerView.Adapter<RecetaAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val nombre: TextView = v.findViewById(R.id.txtNombre)
        val ingredientes: TextView = v.findViewById(R.id.txtIngredientes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_receta, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val receta = lista[position]
        holder.nombre.text = receta.nombre
        holder.ingredientes.text = receta.ingredientes
    }

    override fun getItemCount() = lista.size
}