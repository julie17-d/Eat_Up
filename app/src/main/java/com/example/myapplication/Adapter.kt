package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

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
        val cuisineTypeView : TextView = itemView.findViewById(R.id.cuisine_type)
        fun bind(recipe: RecipeX) {
            imageView.load(recipe.image)
            labelView.text = recipe.label
            cuisineTypeView.text = recipe.dishType.toString()
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe : RecipeX = recipeList[position]
        holder.bind(recipe)
        holder.itemView.setOnClickListener {
            // Ouvre le lien dans un navigateur
            Utils.openBrowser(context, recipe.url)
        }
    }
    override fun getItemCount(): Int = recipeList.size
}