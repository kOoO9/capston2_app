package com.example.loginpage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.loginpage.databinding.ActivityProfessorBinding
import com.google.firebase.auth.FirebaseAuth


class professor : AppCompatActivity() {
    lateinit var binding: ActivityProfessorBinding
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfessorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        val button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener {
            val email = binding.etId.text.toString()
            //val email = "$emailId@professor.com"
            val password = binding.etPass.text.toString()

            if (email.endsWith("@professor.com")) {
                login(email, password)
            } else {
                Toast.makeText(this, "교수용 도메인이 아닙니다.", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }


            login(email, password)
        }
    }

//    private fun setContentView(root: ConstraintLayout?) {
//
//    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent: Intent = Intent(this@professor, professorloginsuccess::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this,"교수 전용 로그인 실패", Toast.LENGTH_SHORT).show()
                    Log.d("Login", "Error : ${task.exception}")
                    val intent: Intent = Intent(this@professor, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }
}


