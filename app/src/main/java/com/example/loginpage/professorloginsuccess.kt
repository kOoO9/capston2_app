package com.example.loginpage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.retrofit.client
import com.example.loginpage.retrofit.jsonclass
import com.example.loginpage.retrofit.retrofit_interface
import recycleadapterprofessor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class professorloginsuccess : AppCompatActivity() {
    private lateinit var retrofitInterface: retrofit_interface
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: recycleadapterprofessor

    private var dataList: MutableList<jsonclass> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professorloginsuccess)

        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.lstUser)
        adapter = recycleadapterprofessor(dataList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        val retrofit = client.getClient()
        retrofitInterface = retrofit?.create(retrofit_interface::class.java)!!

        retrofitInterface.getData().enqueue(object : Callback<List<jsonclass>> {
            override fun onResponse(
                call: Call<List<jsonclass>>,
                response: Response<List<jsonclass>>
            ) {
                val data = response.body()
                println("성공 : ${response.body()}")

                if (data != null) {
                    dataList.addAll(data)
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<jsonclass>>, t: Throwable) {
            }
        })
    }
}

