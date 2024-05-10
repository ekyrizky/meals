package com.ekyrizky.network.service

import com.ekyrizky.network.model.CategoriesResponse
import com.ekyrizky.network.model.MealsResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MealClient @Inject constructor(
    private val service: MealService
) {
    suspend fun fetchCategories(): ApiResponse<CategoriesResponse> =
        service.fetchCategories()


    suspend fun fetchMealsByCategory(category: String): ApiResponse<MealsResponse> =
        service.fetchMealsByCategory(category)

    suspend fun fetchMeal(id: String): ApiResponse<MealsResponse> =
        service.fetchMeal(id)
}