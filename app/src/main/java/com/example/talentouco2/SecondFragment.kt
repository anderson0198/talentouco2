package com.example.talentouco2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.talentouco2.data.database.DAO.ApiLogin
import com.example.talentouco2.data.database.DAO.ApiRegister
import com.example.talentouco2.data.database.entities.Token
import com.example.talentouco2.data.database.entities.User
import com.example.talentouco2.data.database.helper.PreferenceHelper
import com.example.talentouco2.data.database.helper.PreferenceHelper.get
import com.example.talentouco2.data.database.helper.PreferenceHelper.set
import com.example.talentouco2.databinding.FragmentSecondBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val apiRegister : ApiRegister by lazy {
        ApiRegister.create()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registrar.setOnClickListener {
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            performRegister()
        }
        binding.Login.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun performRegister(){
        val textInputUser = binding.editTextUser.text as CharSequence
        val textInputPassword = binding.editTextPassword.text as CharSequence
        val roles: List<String> = listOf(
            "ESTUDIANTE"
        )
        println(textInputUser)
        println(textInputPassword)

        var user = User(textInputUser.toString(),textInputPassword.toString())

        val call = apiRegister.postRegister(user)
        call.enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                println(response.body())
                if (response.isSuccessful){
                    Toast.makeText(context, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

                }else{
                    Toast.makeText(context, "usuario no esta autorizado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                println(t)
            }

        })
    }
}