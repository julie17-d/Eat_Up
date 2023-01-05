package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class second_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)

        val names = arrayOf("Zoe", "Julie", "Faris", "Sandrine", "Woirda")
        val textView = findViewById<TextView>(R.id.arrayView)

        for(name in names) {
            textView.append(name + " ")
        }
    }
}