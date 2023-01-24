package com.example.myapplication.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.`object`.Fetch
import com.example.myapplication.models.HealthModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login = findViewById<Button>(R.id.logBtn)
        val register = findViewById<TextView>(R.id.noAccount)


        login.setOnClickListener{
            val intent =Intent(this, MainActivity::class.java)
            val get = Fetch.service
            val apelle = get.getHealthModel("best", "bf609ed4", "12b3eea3417cea7f8b96953e19937f56", "public")

            apelle.enqueue(object : Callback<HealthModel> {
                override fun onResponse(
                    call: Call<HealthModel>,
                    response: Response<HealthModel>
                ) {
                    if (response.isSuccessful) {
                        Log.e("welcome", "success")

                        val recipes = response.body()!!
                        Log.e("welcome", recipes.hits[0].recipe.label)
                        // Update UI with recipe data
                        for (recipe in recipes.hits) {
                            topRecipeListObject.topRecipeList.add(recipe.recipe)
                        }
                        startActivity(intent)
                    }
                }
                override fun onFailure(call: Call<HealthModel>, t: Throwable) {
                    Log.e("fail", t.message.toString())
                }
            })
        }
        register.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}