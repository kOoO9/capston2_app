package com.example.loginpage.retrofit
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R
import com.example.loginpage.studentloginsuccess

class btnclickadapter (var data: List<lecturestudentjsonclass>, var spinnerinterface: spinnerinterface) : RecyclerView.Adapter<btnclickadapter.ViewHolder>() {
    val chulsuk = listOf("","출석", "결석", "지각")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclebtnclick, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.tv_hakbun.text = item.student_id?.toString()
        holder.tv_name.text = item.student_name
        holder.tv_result.text = item.attendence_mm_dd

        //스피너 생성
        val adapter =
            ArrayAdapter(holder.itemView.context, android.R.layout.simple_spinner_item, chulsuk)
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
                if (selectedChulsuk != "") {
                    var chulsukk = selectedChulsuk
                    var whoareyou = item.student_id?.toString().toString()
                    spinnerinterface.spinnerinterface(chulsukk, whoareyou)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무 항목도 선택되지 않았을 때의 처리를 수행합니다.
            }
        }


        holder.tv_name.setOnClickListener {//학생 이름을 누르면 학생 사진, 전공, 이름, 학번 뜨게 하기
            val context = holder.itemView.context
            val intent = Intent(context, studentinformation::class.java)
            intent.putExtra("student_id", item.student_id?.toString())
            context.startActivity(intent)
            //ContextCompat.startActivity(holder.tv_name.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_hakbun: TextView = itemView.findViewById(R.id.tv_hakbun)
        val tv_name: TextView = itemView.findViewById(R.id.tv_name)
        val tv_result: TextView = itemView.findViewById(R.id.tv_result)

        //스피너 만들기
        val spinner: Spinner = itemView.findViewById(R.id.spinner)

        fun bind(jsonClass: lecturestudentjsonclass) {
            tv_hakbun.text = jsonClass.student_id?.toString()
            tv_name.text = jsonClass.student_name
            tv_result.text = jsonClass.attendence_mm_dd

        }
    }
}

