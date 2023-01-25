package com.example.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.models.RecipeX
import com.example.myapplication.activities.Recipe
import com.example.myapplication.models.Ingredient


class TopRecipeAdapter(private val topRecipeList: ArrayList<RecipeX>, private val context: Context)
    : RecyclerView.Adapter<TopRecipeAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_recipe, parent, false)
        ))
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.carddog)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewTR)
        val labelView: TextView = itemView.findViewById(R.id.nameTR)
        fun bind(recipe: RecipeX) {

            imageView.load(recipe.image)
            labelView.text = recipe.label
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe : RecipeX = topRecipeList[position]
        holder.bind(recipe)
        holder.imageView.setOnClickListener {
            ingRecipeListObject.ingRecipeList.clear()
            for (ing in recipe.ingredients) {
                ingRecipeListObject.ingRecipeList.add(ing)
            }
            Log.e("ingredients", ingRecipeListObject.ingRecipeList[0].image)
            val intent = Intent(context, Recipe::class.java)
            context.startActivity(intent)
            // Ouvre le lien dans un navigateur
            //    Utils.openBrowser(context, recipe.url)
        }
    }
    override fun getItemCount(): Int = topRecipeList.size
}
object ingRecipeListObject {
    var ingRecipeList : ArrayList<Ingredient> = ArrayList()
}