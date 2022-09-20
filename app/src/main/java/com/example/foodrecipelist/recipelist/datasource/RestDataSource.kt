package com.example.foodrecipelist.recipelist.datasource

import com.example.foodrecipelist.recipelist.domain.Constans
import com.example.foodrecipelist.recipelist.model.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface RestDataSource {
    @GET("complexSearch?apiKey=${Constans.TOKEN}")
    suspend fun getRecipeList(): ApiResponse

}