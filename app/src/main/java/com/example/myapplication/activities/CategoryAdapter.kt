package com.example.myapplication.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.RecipeX
import com.example.myapplication.`object`.Utils
import com.example.myapplication.activities.recipeListObject.recipeList
import java.util.Locale.Category

class CategoryAdapter(private val categoryList: ArrayList<Array<String>>, private val context: Context)
    : RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recipe, parent, false)
        ))
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.category_image)
        val nameView: TextView = itemView.findViewById(R.id.category_name)
        fun bind(recipe: RecipeX) {
            imageView.setImageURI(categoryList)
            nameView.text = recipe.label
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