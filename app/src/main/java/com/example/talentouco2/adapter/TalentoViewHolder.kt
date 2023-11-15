package com.example.talentouco2.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.talentouco2.R
import com.example.talentouco2.RecyclerViewFragment
import com.example.talentouco2.data.database.entities.OfertasEntity
import org.w3c.dom.Text

class TalentoViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val foto = view.findViewById<ImageView>(R.id.ivTalento)
    val empresa = view.findViewById<TextView>(R.id.tvOfertaEmpresa)
    val carrera = view.findViewById<TextView>(R.id.tvOfertaCarrera)
    val fecha = view.findViewById<TextView>(R.id.tvOfertaFecha)
    val semestre = view.findViewById<TextView>(R.id.tvOfertaSemestre)
    val descripcion = view.findViewById<TextView>(R.id.tvOfertaDescripcion)

    fun render(oferta: OfertasEntity, imagen: String?){
        empresa.text = oferta.empresa
        carrera.text = oferta.requisitoCarrera
        fecha.text = oferta.fechaInicial
        semestre.text = oferta.requisitoSemestre.toString()
        descripcion.text = oferta.descripcion
        Glide.with(foto.context).load(imagen).into(foto)
    }
}