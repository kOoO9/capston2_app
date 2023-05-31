package com.example.loginpage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.loginpage.databinding.ActivityStudentBinding
import com.google.firebase.auth.FirebaseAuth


class student : AppCompatActivity() {
    lateinit var bindingg: ActivityStudentBinding
    lateinit var Auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingg = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(bindingg.root)

        Auth = FirebaseAuth.getInstance()

        val button = bindingg.btnStu
        button.setOnClickListener {
            val email = bindingg.stuemail.text.toString()
            //val email = "$emailId@student.com"
            val password = bindingg.stupass.text.toString()

            if (email.endsWith("@student.com")) {
                login(email, password)
            } else {
                Toast.makeText(this, "학생용 도메인이 아닙니다.", Toast.LENGTH_SHORT).show()
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        Auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent: Intent = Intent(this, studentloginsuccess::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this,"학생 전용 로그인 실패", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }
}


