package com.example.loginpage.student_login_success

import retrofit2.Call
import retrofit2.http.*

interface student_interface {
    @FormUrlEncoded
    @POST("student_loginsuccess.php")
    fun getData(@Field("username") username: String): Call<List<studentloginjsonclass>> //아이디 전송하고 DB 받아오기
}

interface studentresult_interface {
    @FormUrlEncoded
    @POST("studentresult_success.php")
    fun getData(@Field("lecture_code") lecture_code: String, @Field("student_id") student_id: String): Call<List<studentresultjsonclass>> //아이디 전송하고 DB 받아오기
}

