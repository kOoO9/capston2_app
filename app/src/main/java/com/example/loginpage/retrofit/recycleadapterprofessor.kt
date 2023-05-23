import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R
import com.example.loginpage.retrofit.RecyclerViewbtnclick
import com.example.loginpage.retrofit.jsonclass

class recycleadapterprofessor(var data: List<jsonclass>) : RecyclerView.Adapter<recycleadapterprofessor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_professor, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.tv_lecture.text = item.lecture_name
        holder.tv_code.text = item.lecture_code

        holder.btnExample.setOnClickListener {
            val intent = Intent(holder.btnExample!!.context, RecyclerViewbtnclick::class.java)
            intent.putExtra("lecture_code", item.lecture_code)
            ContextCompat.startActivity(holder.btnExample.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_lecture: TextView = itemView.findViewById(R.id.tv_lecture)
        val tv_code: TextView = itemView.findViewById(R.id.tv_code)
        val btnExample: Button = itemView.findViewById(R.id.btnExample)

        fun bind(jsonClass: jsonclass) {
            tv_lecture.text = jsonClass.lecture_code
            tv_code.text = jsonClass.lecture_name

        }
    }
}
