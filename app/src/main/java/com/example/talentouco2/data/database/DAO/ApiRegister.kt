package com.example.talentouco2.data.database.DAO

import com.example.talentouco2.data.database.entities.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiRegister {

    @POST(value= "register")
    fun postRegister(@Body user: User):
            Call<User>

    companion object Factory{
        private const val BASE_URL = "https://pocapispringboot.azurewebsites.net/"
        fun create() : ApiRegister {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiRegister::class.java)
        }
    }
}