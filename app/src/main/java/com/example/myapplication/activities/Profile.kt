package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.myapplication.R

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val home = findViewById<ImageView>(R.id.home)
        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val profile = findViewById<ImageView>(R.id.profileImg)
        profile.setOnClickListener {
            Log.e("ff","ff")
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}