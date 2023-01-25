package com.example.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.`object`.Fetch
import com.example.myapplication.models.RecipeX
import com.example.myapplication.activities.Recipe
import com.example.myapplication.activities.recipeListObject
import com.example.myapplication.models.HealthModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Adapter(private val recipeList: ArrayList<RecipeX>, private val context: Context)
    : RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recipe, parent, false)
        ))
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.recipe_image)
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

           Log.e("recipe", recipe.label)
           val query = recipe.label
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
                       Log.e("response", recipes.hits[0].recipe.ingredients.toString())
                       // Update UI with recipe data
                       val intent = Intent(context, Recipe::class.java)
                        context.startActivity(intent)
                   }
               }
               override fun onFailure(call: Call<HealthModel>, t: Throwable) {
                   Log.e("fail", t.message.toString())
               }
           })
            // Ouvre le lien dans un navigateur


        //    Utils.openBrowser(context, recipe.url)
        }
    }
    override fun getItemCount(): Int = recipeList.size
}