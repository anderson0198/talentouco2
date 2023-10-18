package com.example.talentouco2.data.database.entities

import com.example.talentouco2.data.database.DAO.ApiLogin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8001/talentoUCO/auth")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiLogin = retrofit.create(ApiLogin::class.java)
}