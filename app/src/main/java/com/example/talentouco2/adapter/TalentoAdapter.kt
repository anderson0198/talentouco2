package com.example.talentouco2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.talentouco2.R
import com.example.talentouco2.Talento

class TalentoAdapter(private val talentoList:List<Talento>): RecyclerView.Adapter<TalentoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalentoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TalentoViewHolder(layoutInflater.inflate(R.layout.item_talento, parent, false))
    }

    override fun getItemCount(): Int = talentoList.size


    override fun onBindViewHolder(holder: TalentoViewHolder, position: Int) {
        val item = talentoList[position]
        holder.render(item)
    }
}