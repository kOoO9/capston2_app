package com.example.loginpage.student_login_success

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R
import com.example.loginpage.retrofit.client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewbtnclick_stu : AppCompatActivity() {

    private lateinit var retrofitInterface: studentresult_interface
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewbtnclick_stu_adapter

    private var dataList: MutableList<studentresultjsonclass> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_viewbtnclick_stu)
        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.thrstUser)
        adapter = RecyclerViewbtnclick_stu_adapter(dataList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //로그인 성공시 받은 아이디
        val lecture_code = intent.getStringExtra("lecture_code")
        println("lecture_code : $lecture_code")
        val student_id = intent.getStringExtra("student_id")
        println("student_id : $student_id")


        val retrofit = client.getClient()
        retrofitInterface = retrofit?.create(studentresult_interface::class.java)!!
        if (lecture_code != null && student_id != null) {
            retrofitInterface.getData(lecture_code, student_id).enqueue(object : Callback<List<studentresultjsonclass>> {
                override fun onResponse(
                    call: Call<List<studentresultjsonclass>>,
                    response: Response<List<studentresultjsonclass>>
                ) {
                    val data = response.body()
                    println("성공공 : ${response.body()}")

                    if (data != null) { //리사이클러뷰
                        dataList.addAll(data)
                        adapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<List<studentresultjsonclass>>, t: Throwable) {
                    println("오류 발생: ${t.message}")
                }
            })
        }
    }
}



