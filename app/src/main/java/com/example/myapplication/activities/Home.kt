package com.example.myapplication.activities
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.HealthModel
import com.example.myapplication.R
import com.example.myapplication.RecipeX
import com.example.myapplication.`object`.Fetch
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, second_page::class.java)
        setContentView(R.layout.activity_eat_up)
        val searchBarText = findViewById<TextInputEditText>(R.id.textSearchB)
        Log.e("e", "e")

        val myEditText = EditText(this)
        myEditText.setOnKeyListener { view, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Code to be executed when the enter key is pressed
                val query = searchBarText.text.toString()
                val fetch = Fetch.service
                // val response = fetch.getRecipes("chicken", "bf609ed4", "12b3eea3417cea7f8b96953e19937f56", "public")
                val call = fetch.getHealthModel(query, "bf609ed4", "12b3eea3417cea7f8b96953e19937f56", "public")

                call.enqueue(object : Callback<HealthModel>{
                    override fun onResponse(
                        call: Call<HealthModel>,
                        response: Response<HealthModel>
                    ) {
                        if (response.isSuccessful) {
                            Log.e("success", "success")

                            val recipes = response.body()!!
                            Log.d("response", recipes.hits[0].recipe.label)
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
                true
            } else {
                false
            }
        }
        //searchBar.setStartIconOnClickListener {

            }
        //val button = findViewById<Button>(R.id.go_second_page)
        //button.setOnClickListener{
            //val intent = Intent(this, second_page::class.java)
            //startActivity(intent)
        //}
    //}
}

object recipeListObject {
    var recipeList : ArrayList<RecipeX> = ArrayList()
}