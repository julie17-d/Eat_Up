package com.example.myapplication.activities

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.CategoryModel

class CategoryAdapter(private val data: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardcat)
        val name: TextView = itemView.findViewById(R.id.category_name)
        val image: ImageView = itemView.findViewById(R.id.category_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val colors = intArrayOf(Color.parseColor("#EC6767"),Color.parseColor("#86BF44"),Color.parseColor("#F6E448"),Color.parseColor("#40B344"),Color.parseColor("#EF8809"),Color.parseColor("#36A33A"))

        holder.name.text = data[position].name
        Log.e("data",data[0].name)
        holder.image.setImageResource(data[position].image)
        holder.cardView.setCardBackgroundColor(colors[position % colors.size])
    }
}
