package com.example.loginpage.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object client {
    private var retrofitClient: Retrofit? = null
    private var gson = GsonBuilder().setLenient().create()

    fun getClient(): Retrofit? {
        if(retrofitClient == null){
            retrofitClient=Retrofit.Builder()
                .baseUrl("http://192.168.50.132")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofitClient
    }
}