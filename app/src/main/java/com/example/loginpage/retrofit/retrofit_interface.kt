package com.example.loginpage.retrofit

import retrofit2.http.GET
import retrofit2.Call

interface retrofit_interface {
    @GET("mysqlconnection.php")
    fun getData(): Call<List<jsonclass>>
}