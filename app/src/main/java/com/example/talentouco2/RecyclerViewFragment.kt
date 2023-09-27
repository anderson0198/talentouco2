package com.example.talentouco2

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.talentouco2.adapter.TalentoAdapter
import com.example.talentouco2.data.database.AppDatabase
import com.example.talentouco2.data.database.entities.OfertasEntity
import com.example.talentouco2.databinding.FragmentRecyclerViewBinding
import com.example.talentouco2.listeners.OnItemClickListener


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecyclerViewFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentRecyclerViewBinding? = null
    private lateinit var db:AppDatabase

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(context)
        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initRecyclerView(){
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerTalentoUCO)

        val ofertas =  arrayOf(
            OfertasEntity(1, "Nacional de Chocolates","17/07/2024","Indefinido", "Ingenieria de Sistemas", 6,"1'800.000","8am-4pm","Empresa local busca desarrolladores de software capaces de desarrollar y mantener software de producción",12456),
            OfertasEntity(2, "Argos","02/02/2024","Indefinido", "Ingenieria Química", 6,"1'500.000","8am-5pm","Descripción generica",12456),
            OfertasEntity(3, "Nestle","07/12/2023","07/06/2024", "Ingenieria industrial Practicas", 6,"1'500.000","8am-2pm","Descripción generica",12456)

        )
        db.ofertasDAO().deleteAll()
        db.ofertasDAO().insertAll(*ofertas)

        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = TalentoAdapter(db.ofertasDAO().getAll())
    }

    override fun onItemClick(view: View, position: Int) {
        TODO("Not yet implemented")
    }


}