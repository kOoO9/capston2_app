package com.example.loginpage.retrofit

import retrofit2.Call
import retrofit2.http.*

interface retrofit_interface {
    @FormUrlEncoded
    @POST("mysqlconnection.php")
    fun getData(@Field("username") username: String): Call<List<jsonclass>> //아이디 전송하고 DB 받아오기
}