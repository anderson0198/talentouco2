package com.example.talentouco2.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.talentouco2.R
import com.example.talentouco2.data.database.entities.OfertasEntity
import com.example.talentouco2.listeners.OnItemClickListener



class TalentoAdapter(var data: List<OfertasEntity> = emptyList(), var Images: List<String?> = emptyList() ): RecyclerView.Adapter<TalentoViewHolder>(){
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalentoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var itemClickListener: OnItemClickListener? = null
        return TalentoViewHolder(layoutInflater.inflate(R.layout.item_talento, parent, false))
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: TalentoViewHolder, position: Int) {
        val oferta = data[position]
        val imagen = Images[position]
        holder.empresa.text = oferta.empresa
        holder.carrera.text = oferta.requisitoCarrera
        holder.fecha.text = oferta.fechaInicial
        holder.semestre.text = "${oferta.requisitoSemestre}"
        holder.descripcion.text = oferta.descripcion

        holder.render(oferta, imagen)
    }
}