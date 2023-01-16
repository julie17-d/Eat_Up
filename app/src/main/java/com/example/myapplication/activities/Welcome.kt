package com.example.myapplication.activities
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R


class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val button = findViewById<TextView>(R.id.go_home)
        val register = findViewById<TextView>(R.id.registerText)
        val login = findViewById<Button>(R.id.login)
        button.setOnClickListener{
            val intent = Intent(this, EatUpActivity::class.java)
            startActivity(intent)
        }
       login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        register.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}