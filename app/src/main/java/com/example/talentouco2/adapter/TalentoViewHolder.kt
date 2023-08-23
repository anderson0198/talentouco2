package com.example.talentouco2.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.talentouco2.R
import com.example.talentouco2.Talento

class TalentoViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val nombre = view.findViewById<TextView>(R.id.tvTalentoNombre)
    val carrera = view.findViewById<TextView>(R.id.tvTalentoCarrera)
    val foto = view.findViewById<ImageView>(R.id.ivTalento)
    fun render(talento: Talento){
        nombre.text = talento.nombre
        carrera.text = talento.carrera
        Glide.with(foto.context).load(talento.foto).into(foto)
    }
}