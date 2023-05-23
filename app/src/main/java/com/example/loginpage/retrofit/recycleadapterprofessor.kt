import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginpage.R
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
    }
    override fun getItemCount(): Int {
        return data.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_lecture: TextView = itemView.findViewById(R.id.tv_lecture)
        val tv_code: TextView = itemView.findViewById(R.id.tv_code)

        fun bind(jsonClass: jsonclass) {
            tv_lecture.text = jsonClass.lecture_code
            tv_code.text = jsonClass.lecture_name
        }
    }
}
