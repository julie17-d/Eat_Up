package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.myapplication.HealthModel
import com.example.myapplication.R
import com.example.myapplication.`object`.Fetch
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, second_page::class.java)

        val searchBarText = findViewById<TextInputEditText>(R.id.textSearchB)

        searchBarText.setOnEditorActionListener(TextView.OnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Log.e("test", "it works!")
                val query = searchBarText.text.toString()
                Log.e("query", query)
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
                            Log.e("response", recipes.hits[0].recipe.label)
                            // Update UI with recipe data
                            for (recipe in recipes.hits) {
                                recipeListObject.recipeList.add(recipe.recipe)
                            }
                            startActivity(intent)
                        }
                    }
                    override fun onFailure(call: Call<HealthModel>, t: Throwable) {
                        Log.e("fail", t.message.toString())
                    }
                })
                return@OnEditorActionListener true
            }
            false
        })
    }
}