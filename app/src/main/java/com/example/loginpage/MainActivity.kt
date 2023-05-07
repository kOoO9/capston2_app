package com.example.loginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settingprofessorbtn()
        settingstudentbtn()
    }

    fun settingprofessorbtn(){
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            //professor로 이동함
            //intent를 사용함 -> 이 서비스나, 액티비티로 가겠다는 의도를 나타냄
            val intent = Intent(this, professor::class.java)
            startActivity(intent)
        }
    }

    fun settingstudentbtn(){
        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            val intent = Intent(this, student::class.java)
            startActivity(intent)
        }
    }
}