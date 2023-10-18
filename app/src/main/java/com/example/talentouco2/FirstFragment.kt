package com.example.talentouco2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.talentouco2.data.database.DAO.ApiLogin
import com.example.talentouco2.data.database.entities.Token
import com.example.talentouco2.data.database.entities.UserCredentials
import com.example.talentouco2.data.database.helper.PreferenceHelper
import com.example.talentouco2.data.database.helper.PreferenceHelper.get
import com.example.talentouco2.data.database.helper.PreferenceHelper.set
import com.example.talentouco2.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val apiLogin : ApiLogin by lazy {
        ApiLogin.create()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

        val preferences = PreferenceHelper.defaultPrefs(context)
        if (preferences["token", ""].contains(".")) {

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.Login.setOnClickListener {
            //findNavController().navigate(R.id.action_login_to_recyclerActivity)
            performLogin()
        }
        binding.Register.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun createSessionPreference(jwt : String){
        val preferences = PreferenceHelper.defaultPrefs(context)
        preferences["token"] = jwt
    }

    private fun performLogin(){
        val textInputUser = binding.editTextEmail.text as CharSequence
        val textInputPassword = binding.editTextPassword.text as CharSequence
        println(textInputUser)
        println(textInputPassword)

        var userCredentials = UserCredentials(textInputUser.toString(),textInputPassword.toString())

        val call = apiLogin.putLogin(userCredentials)
        call.enqueue(object : Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                println(response.body())
                if (response.isSuccessful){
                    response.body()?.let { createSessionPreference(it.token) }
                    //ir a recycler
                    findNavController().navigate(R.id.action_login_to_recyclerActivity)

                }else{
                    Toast.makeText(context, "usuario no esta autorizado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                Toast.makeText(context, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
                println(t)
            }

        })
    }


}