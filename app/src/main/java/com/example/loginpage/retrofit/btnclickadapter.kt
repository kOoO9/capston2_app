package com.example.loginpage.retrofit
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R

class btnclickadapter (var data: List<lecturestudentjsonclass>) : RecyclerView.Adapter<btnclickadapter.ViewHolder>() {

    val chulsuk = listOf("출석", "결석", "지각")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclebtnclick, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //스피너 생성
        val adapter = ArrayAdapter(holder.itemView.context, android.R.layout.simple_spinner_item, chulsuk)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.spinner.adapter = adapter
        holder.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                val selectedChulsuk = chulsuk[pos]
                // 선택된 항목에 대한 처리를 수행합니다.
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무 항목도 선택되지 않았을 때의 처리를 수행합니다.
            }
        }

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
        //스피너 만들기
        val spinner: Spinner = itemView.findViewById(R.id.spinner)

        fun bind(jsonClass: lecturestudentjsonclass) {
            tv_hakbun.text = jsonClass.student_id?.toString()
            tv_name.text = jsonClass.student_name

        }
    }
}

