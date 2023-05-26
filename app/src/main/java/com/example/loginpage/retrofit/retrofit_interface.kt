package com.example.loginpage.retrofit

import retrofit2.Call
import retrofit2.http.*

interface retrofit_interface {
    @FormUrlEncoded
    @POST("mysqlconnection.php")
    fun getData(@Field("username") username: String): Call<List<jsonclass>> //아이디 전송하고 DB 받아오기
}

interface studentinterface {
    @FormUrlEncoded
    @POST("student_where.php")
    fun getData(@Field("lecturecode") lecturecode: String, @Field("attendence_mm_dd") attendence_mm_dd: String): Call<List<lecturestudentjsonclass>> //코드 전송하고 DB 받아오기
}