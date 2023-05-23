package com.example.loginpage.retrofit
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R

class btnclickadapter (var data: List<lecturestudentjsonclass>) : RecyclerView.Adapter<btnclickadapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclebtnclick, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.tv_hakbun.text = item.student_id?.toString()
        holder.tv_name.text = item.student_name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_hakbun: TextView = itemView.findViewById(R.id.tv_hakbun)
        val tv_name: TextView = itemView.findViewById(R.id.tv_name)

        fun bind(jsonClass: lecturestudentjsonclass) {
            tv_hakbun.text = jsonClass.student_id?.toString()
            tv_name.text = jsonClass.student_name

        }
    }
}
