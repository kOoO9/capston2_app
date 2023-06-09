package com.example.loginpage.retrofit

data class jsonclass(
    val lecture_code : String?,
    val lecture_name : String?,
)

data class lecturestudentjsonclass(
    val student_id : Int?,
    val student_name : String?,
    val attendence_mm_dd : String?,
)

data class studentinformationjsonclass(
    val student_id : Int?,
    val student_name : String?,
    val collegemajor : String?,
    val imgurl : String?,
)

data class loginsuccessbooltionjsonclass(
    val status : String?,
    val message : String?,
)