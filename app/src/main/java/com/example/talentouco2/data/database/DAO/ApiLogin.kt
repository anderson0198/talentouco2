package com.example.talentouco2.data.database.DAO

import com.example.talentouco2.data.database.entities.Token
import com.example.talentouco2.data.database.entities.User
import com.example.talentouco2.data.database.entities.UserCredentials
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiLogin {
    @POST(value = "authenticate")
    fun putLogin(@Body userCred: UserCredentials):
            Call<Token>
    companion object Factory{
        private const val BASE_URL = "https://pocapispringboot.azurewebsites.net/"
        fun create() : ApiLogin {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiLogin::class.java)
        }
    }




}