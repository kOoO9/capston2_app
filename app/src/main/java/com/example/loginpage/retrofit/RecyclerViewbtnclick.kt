package com.example.loginpage.retrofit

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.*

class RecyclerViewbtnclick : AppCompatActivity() {
    private lateinit var retrofitInterface: studentinterface
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: btnclickadapter
    private var dataList: MutableList<lecturestudentjsonclass> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_viewbtnclick)

        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.twostUser)
        adapter = btnclickadapter(dataList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val textt = findViewById<TextView>(R.id.calender)
//        val date: LocalDate = LocalDate.now()
//        textt.text = date.toString()

//        val calendar = Calendar.getInstance()
//        val month = calendar.get(Calendar.MONTH)
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//        var attendence_mm_dd = "attendence_${month + 1}_${day}"
//        println("오늘은 ${attendence_mm_dd}")
        var attendence_mm_dd = "hi"

        val lectureCode = intent.getStringExtra("lecture_code")
        if (lectureCode != null) {
            println("lectureCode: $lectureCode");
        }

        //버튼 클릭하면 날짜 선택 가능
        val button = findViewById<Button>(R.id.button3)
        button.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // DatePickerDialog 생성
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedYear}-${selectedMonth + 1}-${selectedDay}"
                button.text = selectedDate
                textt.text = selectedDate
                if(selectedMonth + 1 < 10){
                    attendence_mm_dd = "attendence_0${selectedMonth + 1}_${selectedDay}"
                }
                else{
                    attendence_mm_dd = "attendence_${selectedMonth + 1}_${selectedDay}"
                }
                dataList.clear()
                perform(lectureCode,attendence_mm_dd)

            }, year, month, day)
            // DatePickerDialog 표시
            datePickerDialog.show()
        }

//        fun perform(lectureCode: String,attendence_mm_dd: String){
//                val retrofit = client.getClient()
//                retrofitInterface = retrofit?.create(studentinterface::class.java)!!
//                if (lectureCode != null) {
//                    retrofitInterface.getData(lectureCode, attendence_mm_dd).enqueue(object : Callback<List<lecturestudentjsonclass>> {
//                        override fun onResponse(
//                            call: Call<List<lecturestudentjsonclass>>,
//                            response: Response<List<lecturestudentjsonclass>>
//                        ) {
//                            val data = response.body()
//                            println("성공 : ${response.body()}")
//                            println("하하 : ${attendence_mm_dd}")
//
//                            if (data != null) { //리사이클러뷰
//                                dataList.addAll(data)
//                                adapter.notifyDataSetChanged()
//                            }
//                        }
//                        override fun onFailure(call: Call<List<lecturestudentjsonclass>>, t: Throwable) {
//                        }
//                    })
//                }
//         }

        if (lectureCode != null) {
            perform(lectureCode,attendence_mm_dd)
        }

    }

    private fun perform(lectureCode: String?, attendence_mm_dd: String) {
        val retrofit = client.getClient()
        retrofitInterface = retrofit?.create(studentinterface::class.java)!!
        if (lectureCode != null) {
            retrofitInterface.getData(lectureCode, attendence_mm_dd).enqueue(object : Callback<List<lecturestudentjsonclass>> {
                override fun onResponse(
                    call: Call<List<lecturestudentjsonclass>>,
                    response: Response<List<lecturestudentjsonclass>>
                ) {
                    val data = response.body()
                    println("성공 : ${response.body()}")
                    println("하하 : ${attendence_mm_dd}")

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

