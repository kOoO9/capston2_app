package com.example.loginpage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginpage.databinding.ActivityStudentBinding
import com.example.loginpage.retrofit.client
import com.example.loginpage.retrofit.loginsuccessboolinterface
import com.example.loginpage.retrofit.loginsuccessbooltionjsonclass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class student : AppCompatActivity() {
    private lateinit var retrofitInterface: loginsuccessboolinterface
    lateinit var bindingg: ActivityStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingg = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(bindingg.root)

        val button = bindingg.btnStu
        button.setOnClickListener {
            val email = bindingg.stuemail.text.toString()
            val password = bindingg.stupass.text.toString()
            val student = "stu"

            val retrofit = client.getClient()
            retrofitInterface = retrofit?.create(loginsuccessboolinterface::class.java)!!
            if (email != null) {
                retrofitInterface.getData(student, email, password)
                    .enqueue(object : Callback<List<loginsuccessbooltionjsonclass>> {
                        override fun onResponse(
                            call: Call<List<loginsuccessbooltionjsonclass>>,
                            response: Response<List<loginsuccessbooltionjsonclass>>
                        ) {
                            val data = response.body()
                            println("gkgkgk : $email")
                            if (data != null) {
                                for (i in data){
                                    if(i.status == "true"){
                                        val intent: Intent = Intent(this@student, studentloginsuccess::class.java)
                                        intent.putExtra("email", email)
                                        startActivity(intent)
                                        finish()
                                    } else if(i.status == "false"){
                                        Toast.makeText(this@student,"학생 전용 로그인 실패", Toast.LENGTH_SHORT).show()
                                        val intent: Intent = Intent(this@student, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                }
                            }

                        }
                        override fun onFailure(
                            call: Call<List<loginsuccessbooltionjsonclass>>,
                            t: Throwable
                        ) {}
                    }
                    )
            }
        }
    }
}





//    private fun login(email: String, password: String) {
//        Auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    val intent: Intent = Intent(this, studentloginsuccess::class.java)
//                    intent.putExtra("email", email)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    Toast.makeText(this,"학생 전용 로그인 실패", Toast.LENGTH_SHORT).show()
//                    val intent: Intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//            }
//    }
//}
//
//
