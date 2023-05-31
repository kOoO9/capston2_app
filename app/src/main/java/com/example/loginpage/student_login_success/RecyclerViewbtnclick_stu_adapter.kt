package com.example.loginpage.student_login_success
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R
import java.util.*

class RecyclerViewbtnclick_stu_adapter (var data: List<studentresultjsonclass>) : RecyclerView.Adapter<RecyclerViewbtnclick_stu_adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclebtnclick_adapter_stu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val calendar:Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)

        val item = data[position]
        //holder.tv_day.text = item.lecture_day
        holder.tv_dayresult.text = item.lecture_result

        val inputString = item.lecture_day
        val date = inputString?.substringAfter("_") // "05_24"
        val month = date?.substring(0, 2) // "05"
        val day = date?.substring(3, 5) // "24"

        val new = "${year}년 ${month}월 ${day}일"
        holder.tv_day.text = new
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_day: TextView = itemView.findViewById(R.id.tv_day)
        val tv_dayresult: TextView = itemView.findViewById(R.id.tv_dayresult)

        //스피너 만들기

        fun bind(jsonClass: studentresultjsonclass) {
            tv_day.text = jsonClass.lecture_day?.toString()
            tv_dayresult.text = jsonClass.lecture_result
        }
    }
}

