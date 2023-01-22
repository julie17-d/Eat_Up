package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.myapplication.R

class Questionairre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionairre)
        val ageInput = findViewById<EditText>(R.id.age_input)
        val age = ageInput.text.toString()
        val heightInput = findViewById<EditText>(R.id.height_input)
        val height = heightInput.text.toString()
        val otherCheckbox = findViewById<CheckBox>(R.id.other_checkbox)
        val otherInput = findViewById<EditText>(R.id.other_input)
        otherCheckbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                otherInput.visibility = View.VISIBLE
            } else {
                otherInput.visibility = View.GONE
            }
        }
        val register = findViewById<Button>(R.id.Submit)
        register.setOnClickListener{
            Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}