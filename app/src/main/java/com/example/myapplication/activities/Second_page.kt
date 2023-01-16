package com.example.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class second_page : AppCompatActivity() {
    //var recipeList : ArrayList<RecipeX> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)
        //remplirLesPlanetes()
        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@second_page)
            adapter = Adapter(recipeListObject.recipeList, context)
        }
    }
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)

        val names = arrayOf("Zoe", "Julie", "Faris", "Sandrine", "Woirda")
        val textView = findViewById<TextView>(R.id.arrayView)

        for(name in names) {
            textView.append(name + " ")
        }
    }*/
}