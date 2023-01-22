package com.example.myapplication.ViewModel

import RecipeRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.HealthModel
import com.example.myapplication.`object`.Fetch
import com.example.myapplication.models.RecipeX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val repository = RecipeRepository()
    var recipeList: LiveData<List<RecipeX>>

    init {
        recipeList = repository.getRecipes("best")
    }

    fun getRecipes(query: String) {
        recipeList = repository.getRecipes(query)
    }
}
