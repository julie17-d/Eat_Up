package com.example.myapplication.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.HealthModel
import com.example.myapplication.R
import com.example.myapplication.ViewModel.MainViewModel
import com.example.myapplication.models.RecipeX
import com.example.myapplication.`object`.Fetch
import com.example.myapplication.adapters.CategoryAdapter
import com.example.myapplication.database.categoryArray
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, second_page::class.java)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_category)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = CategoryAdapter(categoryArray)



        val searchBarText = findViewById<TextInputEditText>(R.id.textSearchB)

        searchBarText.setOnEditorActionListener(TextView.OnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Log.e("test", "it works!")
                val query = searchBarText.text.toString()
                Log.e("query", query)
                val fetch = Fetch.service
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

        val home = findViewById<ImageView>(R.id.home)
        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
        }
        val profile = findViewById<ImageView>(R.id.profileImg)
        profile.setOnClickListener {
            Log.e("ff","ff")
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val best = viewModel.getRecipes("best")
        val recyclerView2: RecyclerView = findViewById(R.id.top_recycler)
        val messages = listOf("Les mêmes ingrédients dans les vinaigrettes et les écrans solaires", "Du soda qui résiste au feu", "Du café champion des antioxydants","De la bière anti-caries","An avocado has more than twice as much potassium as a banana.","one lemon contains your daily dose of vitamin C, it cleanses the liver, boosts your immunity and aids in weight loss."
                )
        val facts = findViewById<FloatingActionButton>(R.id.notification_facts)
        facts.setOnClickListener {
            val randomMessage = messages.random()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Pop-up Title")
            builder.setMessage(randomMessage)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }



    }
}

object recipeListObject {
    var recipeList : ArrayList<RecipeX> = ArrayList()
}