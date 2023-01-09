package com.example.myapplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Fetch {
    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    val builderRetro = Retrofit.Builder()
        .baseUrl("https://api.edamam.com/api/recipes/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val service = builderRetro.create(GetApi::class.java)


}