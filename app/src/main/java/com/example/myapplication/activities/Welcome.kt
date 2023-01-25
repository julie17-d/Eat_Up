package com.example.myapplication.activities
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.`object`.Fetch
import com.example.myapplication.models.HealthModel
import com.example.myapplication.models.RecipeX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val button = findViewById<TextView>(R.id.go_home)
        val register = findViewById<TextView>(R.id.registerText)
        val login = findViewById<Button>(R.id.login)
        val intent = Intent(this, MainActivity::class.java)



        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            Log.e("top", topRecipeListObject.toString())

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
object topRecipeListObject {
    var topRecipeList : ArrayList<RecipeX> = ArrayList()
}