package com.example.loginpage.retrofit

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RecyclerViewbtnclick : AppCompatActivity(), spinnerinterface {
    private lateinit var retrofitInterface: studentinterface
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: btnclickadapter
    private var dataList: MutableList<lecturestudentjsonclass> = mutableListOf()

    private val idList: MutableList<String> = mutableListOf() //학번저장
    private val idwhere: MutableList<String> = mutableListOf() // 출석 결과 저장
    var jsonjson = "hi"

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_viewbtnclick)

        //스피너 값 전송
        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.twostUser)
        adapter = btnclickadapter(dataList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val textt = findViewById<TextView>(R.id.calender)
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
                if(selectedMonth + 1 < 10 && selectedDay > 10){
                    attendence_mm_dd = "attendence_0${selectedMonth + 1}_${selectedDay}"
                }
                else if(selectedMonth + 1 < 10 && selectedDay < 10){
                    attendence_mm_dd = "attendence_0${selectedMonth + 1}_0${selectedDay}"
                }
                else if(selectedMonth + 1 > 10 && selectedDay < 10){
                    attendence_mm_dd = "attendence_${selectedMonth + 1}_0${selectedDay}"
                }
                else{
                    attendence_mm_dd = "attendence_${selectedMonth + 1}_${selectedDay}"
                }

                dataList.clear()
                idList.clear()
                idwhere.clear()
                jsonjson = "hi"
                perform(lectureCode,attendence_mm_dd, jsonjson)

            }, year, month, day)
            // DatePickerDialog 표시
            datePickerDialog.show()
        }

        if (lectureCode != null) {
            perform(lectureCode,attendence_mm_dd, jsonjson)
        }

        //출석 수정 버튼 클릭
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {//출석 수정 버튼 누르면
            println("whoareyou : $idList $idwhere") //학번만 따로 리스트에 저장

            convertListToJsonArray(idList,idwhere)

            perform(lectureCode,attendence_mm_dd, jsonjson)
            //함수 수행하고 클리어
            dataList.clear()
            idList.clear()
            idwhere.clear()
            jsonjson = "hi"

        }
    }

    override fun spinnerinterface(chulsuk: String, whoareyou: String) {
        for (i in idList.indices){
            if(idList[i] == whoareyou){
                idwhere[i] = chulsuk
            }
        }
        println("누구누구 : ${idList} $idwhere")

    }


    private fun perform(lectureCode: String?, attendence_mm_dd: String, jsonarray: String) {
        val retrofit = client.getClient()
        retrofitInterface = retrofit?.create(studentinterface::class.java)!!
        if (lectureCode != null) {
            retrofitInterface.getData(lectureCode, attendence_mm_dd, jsonarray).enqueue(object : Callback<List<lecturestudentjsonclass>> {
                override fun onResponse(
                    call: Call<List<lecturestudentjsonclass>>,
                    response: Response<List<lecturestudentjsonclass>>
                ) {
                    val data = response.body()
                    data?.let { dataList ->
                        for (item in dataList) {
                            item.student_id?.let { idList.add(it.toString()) }
                        }
                    }
                    data?.let { dataList ->
                        for (item in dataList) {
                            item.attendence_mm_dd?.let { idwhere.add(it) }
                        }
                    }

                    println("성공 : ${response.body()}")
                    println("하하 : ${attendence_mm_dd}")
                    println("idList : $idList") //학번만 따로 리스트에 저장
                    println("idwhere : $idwhere") //학번만 따로 리스트에 저장
                    println("jsonstring_성공 : ${jsonarray}")

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


    fun convertListToJsonArray(id: List<String>,result: List<String>){
        val array: ArrayList<Data> = ArrayList()

        for (i in idList.indices) {
            val friend = Data(idList[i], result[i])
            array.add(friend)
        }


        //  List를 JsonArray로 만들어서 보낸다 .
        var makeGson = GsonBuilder().create()
        var listType: TypeToken<ArrayList<Data>> = object : TypeToken<ArrayList<Data>>() {}

        try {
            // 제이슨으로 변환
            jsonjson = makeGson.toJson(array,listType.type)
            println("머지 : $jsonjson")
        }catch (e : JSONException){
        }
    }


    private fun notifyDataSetChanged() {
        TODO("Not yet implemented")
    }
}

data class Data(
    val id: String,
    val result: String,
)