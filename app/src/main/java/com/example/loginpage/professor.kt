package com.example.loginpage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.loginpage.databinding.ActivityProfessorBinding
import com.example.loginpage.retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class professor : AppCompatActivity() {
    private lateinit var retrofitInterface: loginsuccessboolinterface
    lateinit var binding: ActivityProfessorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfessorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener {
            val email = binding.etId.text.toString()
            val password = binding.etPass.text.toString()
            val professor = "pro"

            //perform(professor ,email, password)
            val retrofit = client.getClient()
            retrofitInterface = retrofit?.create(loginsuccessboolinterface::class.java)!!
            if (email != null) {
                retrofitInterface.getData(professor,email, password)
                    .enqueue(object : Callback<List<loginsuccessbooltionjsonclass>> {
                        override fun onResponse(
                            call: Call<List<loginsuccessbooltionjsonclass>>,
                            response: Response<List<loginsuccessbooltionjsonclass>>
                        ) {
                            val data = response.body()
                            if (data != null) {
                                for (i in data){
                                    if(i.status == "true"){
                                        val intent: Intent = Intent(this@professor, professorloginsuccess::class.java) //로그인 성공 email 아이디 확인
                                        intent.putExtra("email", email)
                                        startActivity(intent)
                                        finish()
                                    } else if(i.status == "false"){
                                        Toast.makeText(this@professor,"아이디 또는 비밀번호를 확인해 주세요", Toast.LENGTH_SHORT).show()
                                        val intent: Intent = Intent(this@professor, MainActivity::class.java)
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




