package com.example.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.RecipeX
import com.example.myapplication.`object`.Utils
import com.example.myapplication.activities.Recipe
import com.example.myapplication.activities.RegistrationActivity

class Adapter(private val recipeList: ArrayList<RecipeX>, private val context: Context)
    : RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recipe, parent, false)
        ))
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val labelView: TextView = itemView.findViewById(R.id.label)
        fun bind(recipe: RecipeX) {
            imageView.load(recipe.image)
            labelView.text = recipe.label
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe : RecipeX = recipeList[position]
        holder.bind(recipe)
       holder.itemView.setOnClickListener {
            // Ouvre le lien dans un navigateur
           val intent = Intent(context, Recipe::class.java)
           context.startActivity(intent)
        //    Utils.openBrowser(context, recipe.url)
        }
    }
    override fun getItemCount(): Int = recipeList.size
}