package com.ekyrizky.network.service

import com.ekyrizky.network.model.CategoriesResponse
import com.ekyrizky.network.model.MealsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET("categories.php")
    suspend fun fetchCategories(): ApiResponse<CategoriesResponse>

    @GET("filter.php?c=chicken")
    suspend fun fetchMealsByCategory (
        @Query("c") category: String
    ): ApiResponse<MealsResponse>

    @GET("lookup.php?i=52878")
    suspend fun fetchMeal (
        @Query("i") id: String
    ): ApiResponse<MealsResponse>
}