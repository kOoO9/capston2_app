package com.example.loginpage.retrofit

import android.text.Editable
import org.json.JSONArray
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
    fun getData(@Field("lecturecode") lecturecode: String, @Field("attendence_mm_dd") attendence_mm_dd: String,
                @Field("jsonarray") jsonarray: String
    ):
            Call<List<lecturestudentjsonclass>> //코드 전송하고 DB 받아오기
}

interface studentinformationinterface {
    @FormUrlEncoded
    @POST("student_information.php")
    fun getData(@Field("student_id") student_id: String): Call<List<studentinformationjsonclass>> //학번 전송하고 학생 정보 받아오기
}

interface loginsuccessboolinterface {
    @FormUrlEncoded
    @POST("loginbool.php")
    fun getData(@Field("who") who: String, @Field("id") id: String, @Field("pw") pw: String): Call<List<loginsuccessbooltionjsonclass>>
}



