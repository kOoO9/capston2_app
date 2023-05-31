package com.example.loginpage.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.loginpage.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class studentinformation : AppCompatActivity(){
    private lateinit var retrofitInterface: studentinformationinterface
    private var dataList: MutableList<studentinformationjsonclass> = mutableListOf()
    private var student_id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentinformation)

        student_id = intent.getStringExtra("student_id").toString()
        if (student_id != null) {
            println("studentinformation_student_id: $student_id")
            perform(student_id)
        }
    }

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed() // 기본 뒤로가기 동작 수행
        // 이전 화면으로 돌아가는 추가 동작
    }

    private fun perform(student_id: String?) {
        val retrofit = client.getClient()
        retrofitInterface = retrofit?.create(studentinformationinterface::class.java)!!
        if (student_id != null) {
            retrofitInterface.getData(student_id)
                .enqueue(object : Callback<List<studentinformationjsonclass>> {
                    override fun onResponse(
                        call: Call<List<studentinformationjsonclass>>,
                        response: Response<List<studentinformationjsonclass>>
                    ) {
                        val data = response.body()

                        val tv_name = findViewById<TextView>(R.id.tv_name)
                        val tv_codee = findViewById<TextView>(R.id.tv_codee)
                        val tv_collegemajor = findViewById<TextView>(R.id.tv_collegemajor)

                        println(data)
                        data?.let {
                            val Student = it.firstOrNull()
                            val studentid = Student?.student_id
                            val student_name = Student?.student_name
                            val collegemajor = Student?.collegemajor
                            val imgurl = Student?.imgurl
                            tv_name.text = student_name
                            tv_codee.text = studentid.toString()
                            tv_collegemajor.text = collegemajor
                            if(imgurl?.length ?:0  >= 10){
                                val context = applicationContext
                                Glide.with(context)
                                    .load(imgurl)
                                    .into(findViewById(R.id.imageView4))
                            }
                            else{
                                println("널이지나")
                            }
                        }

                    }

                    override fun onFailure(
                        call: Call<List<studentinformationjsonclass>>,
                        t: Throwable
                    ) {}
                })
        }
    }
}

