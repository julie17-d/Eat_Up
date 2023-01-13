package com.example.myapplication.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.Home
import com.example.myapplication.R

class RegistrationActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val register = findViewById<Button>(R.id.logBtn)
        val login = findViewById<TextView>(R.id.alreadyReg)
        register.setOnClickListener{
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
        login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}