package com.example.myapplication.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.models.Ingredient


class IngredientAdapter(private val ingrecipeList: ArrayList<Ingredient>, private val context: Context)
    : RecyclerView.Adapter<IngredientAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ingredients, parent, false)
        ))
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ing_image)
        val labelView: TextView = itemView.findViewById(R.id.ing_name)
        val measure : TextView =itemView.findViewById(R.id.ing_measure)
        val quantity : TextView =itemView.findViewById(R.id.ing_quantity)
        fun bind(ing: Ingredient) {
            imageView.load(ing.image)
            labelView.text = ing.food
            measure.text = ing.measure
            quantity.text = ing.quantity.toString()
        }

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe : Ingredient = ingrecipeList[position]
        holder.bind(recipe)
    }
    override fun getItemCount(): Int = ingrecipeList.size

}
