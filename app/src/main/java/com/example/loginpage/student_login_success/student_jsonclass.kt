package com.example.loginpage.student_login_success

data class studentloginjsonclass(
    val lecture_code : String?,
    val lecture_name : String?,
    val professor : String?,
    val student_id : String?,
    )

data class studentresultjsonclass(
    val lecture_day : String?,
    val lecture_result : String?,
)
