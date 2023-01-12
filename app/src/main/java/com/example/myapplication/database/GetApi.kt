package com.example.myapplication.database
import com.example.myapplication.HealthModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetApi {
    @GET("v2")
    fun getHealthModel(
        @Query("q") q: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String,
        @Query("type") type :String
    ): Call<HealthModel>
}