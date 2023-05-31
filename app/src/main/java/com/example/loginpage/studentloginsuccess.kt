package com.example.loginpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.retrofit.client
import com.example.loginpage.retrofit.jsonclass
import com.example.loginpage.student_login_success.recycleadapterstudent
import com.example.loginpage.student_login_success.student_interface
import com.example.loginpage.student_login_success.studentloginjsonclass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class studentloginsuccess : AppCompatActivity() {

    private lateinit var retrofitInterface: student_interface
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: recycleadapterstudent

    private var dataList: MutableList<studentloginjsonclass> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentloginsuccess)
        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.lstUser)
        adapter = recycleadapterstudent(dataList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //로그인 성공시 받은 아이디
        val email = intent.getStringExtra("email")
        val username = email?.substringBefore("@")
        println("아이디 : $username")


        val retrofit = client.getClient()
        retrofitInterface = retrofit?.create(student_interface::class.java)!!
        if (username != null) {
            retrofitInterface.getData(username).enqueue(object : Callback<List<studentloginjsonclass>> {
                override fun onResponse(
                    call: Call<List<studentloginjsonclass>>,
                    response: Response<List<studentloginjsonclass>>
                ) {
                    val data = response.body()
                    println("성공 : ${response.body()}")

                    if (data != null) { //리사이클러뷰
                        dataList.addAll(data)
                        adapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<List<studentloginjsonclass>>, t: Throwable) {
                }
            })
        }
    }
}


