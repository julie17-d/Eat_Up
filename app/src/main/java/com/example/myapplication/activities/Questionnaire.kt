package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.myapplication.R
import com.example.myapplication.`object`.Fetch
import com.example.myapplication.models.HealthModel
import com.example.myapplication.models.RecipeX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Questionnaire : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)
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
           val intent = Intent(this, MainActivity::class.java)
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
            })        }
    }
}
