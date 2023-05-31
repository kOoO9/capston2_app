package com.example.loginpage.student_login_success
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R
import com.example.loginpage.retrofit.RecyclerViewbtnclick
import com.example.loginpage.retrofit.jsonclass

class recycleadapterstudent(var data: MutableList<studentloginjsonclass>) : RecyclerView.Adapter<recycleadapterstudent.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_student, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.tv_lecture_stu.text = item.lecture_name
        holder.tv_code_stu.text = item.lecture_code
        holder.tv_professor_stu.text = item.professor

        holder.btnExample_stu.setOnClickListener {
            val intent = Intent(holder.btnExample_stu!!.context, RecyclerViewbtnclick_stu::class.java)
            intent.putExtra("lecture_code", item.lecture_code)
            intent.putExtra("student_id", item.student_id)

            ContextCompat.startActivity(holder.btnExample_stu.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_lecture_stu: TextView = itemView.findViewById(R.id.tv_lecture_stu)
        val tv_code_stu: TextView = itemView.findViewById(R.id.tv_code_stu)
        val tv_professor_stu: TextView = itemView.findViewById(R.id.tv_professor_stu)
        val btnExample_stu: Button = itemView.findViewById(R.id.btnExample_stu)

        fun bind(jsonClass: studentloginjsonclass) {
            tv_lecture_stu.text = jsonClass.lecture_code
            tv_code_stu.text = jsonClass.lecture_name
            tv_professor_stu.text = jsonClass.professor

        }
    }
}
