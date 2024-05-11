package com.ekyrizky.data.util

import com.ekyrizky.network.model.CategoriesResponse
import com.ekyrizky.network.model.MealCategoryResponse
import com.ekyrizky.network.model.MealResponse
import com.ekyrizky.network.model.MealsResponse

object MockUtil {
    private fun mockCategoryResponse() = MealCategoryResponse(
        name = "Beef",
        image = "https://www.themealdb.com/images/category/beef.png",
        description = "Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]"
    )

    fun mockCategoryListResponse() = CategoriesResponse(categories = listOf(mockCategoryResponse()))

    private fun mockMealResponse() = MealResponse(
        id = "52874",
        name = "Beef and Mustard Pie",
        image = "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
        origin = "",
        instructions = ""
    )

    fun mockMealSResponse() = MealsResponse(meals = listOf(mockMealResponse()))
}