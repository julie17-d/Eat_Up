package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.IngredientAdapter
import com.example.myapplication.adapters.ingRecipeListObject

class Recipe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)


        val recyclerView : RecyclerView = findViewById(R.id.ing_rv)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@Recipe, LinearLayoutManager.HORIZONTAL, false)
            adapter = IngredientAdapter(ingRecipeListObject.ingRecipeList, context)

        }
    }
}