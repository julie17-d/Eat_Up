package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val get = findViewById<Button>(R.id.ApiBtn)
        val searchBarText = findViewById<TextInputEditText>(R.id.text_search_bar) as EditText
        get.setOnClickListener {
            val query = searchBarText.text.toString()
            val fetch = Fetch.service
            // val response = fetch.getRecipes("chicken", "bf609ed4", "12b3eea3417cea7f8b96953e19937f56", "public")
            val call = fetch.getHealthModel(query, "bf609ed4", "12b3eea3417cea7f8b96953e19937f56", "public")

            call.enqueue(object : Callback<HealthModel> {
                override fun onResponse(
                    call: Call<HealthModel>,
                    response: Response<HealthModel>
                ) {
                    if (response.isSuccessful) {
                        Log.e("success", "success")

                        val recipes = response.body()!!
                        Log.d("response", recipes.hits[0].recipe.label)
                        // Update UI with recipe data
                    }
                }
                override fun onFailure(call: Call<HealthModel>, t: Throwable) {
                    Log.e("fail", t.message.toString())
                }
            })
        }
        val button = findViewById<Button>(R.id.go_second_page)
        button.setOnClickListener{
            val intent = Intent(this, second_page::class.java)
            startActivity(intent)
        }
    }
}