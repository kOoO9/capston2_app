package com.example.loginpage.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewbtnclick : AppCompatActivity() {
    private lateinit var retrofitInterface: studentinterface
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: btnclickadapter
    private var dataList: MutableList<lecturestudentjsonclass> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_viewbtnclick)

        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.twostUser)
        adapter = btnclickadapter(dataList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val lectureCode = intent.getStringExtra("lecture_code")
        if (lectureCode != null) {
            println("lectureCode: $lectureCode")
        }

        val retrofit = client.getClient()
        retrofitInterface = retrofit?.create(studentinterface::class.java)!!
        if (lectureCode != null) {
            retrofitInterface.getData(lectureCode).enqueue(object : Callback<List<lecturestudentjsonclass>> {
                override fun onResponse(
                    call: Call<List<lecturestudentjsonclass>>,
                    response: Response<List<lecturestudentjsonclass>>
                ) {
                    val data = response.body()
                    println("성공 : ${response.body()}")

                    if (data != null) { //리사이클러뷰
                        dataList.addAll(data)
                        adapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<List<lecturestudentjsonclass>>, t: Throwable) {
                }
            })
        }
    }

    private fun notifyDataSetChanged() {
        TODO("Not yet implemented")
    }
}

