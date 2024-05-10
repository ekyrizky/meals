package com.ekyrizky.network

import com.ekyrizky.network.model.MealCategoryResponse
import com.ekyrizky.network.model.MealResponse

object MockUtil {

    fun mockCategory() = MealCategoryResponse(
        id = "1",
        name = "Beef",
        image = "https://www.themealdb.com/images/category/beef.png",
        desc = "Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]"
    )

    fun mockCategoryList() = listOf(mockCategory())

    fun mockMeal() = MealResponse(
        id = "52874",
        name = "Beef and Mustard Pie",
        image = "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
        origin = null,
        instructions = null
    )

    fun mockMealList() = listOf(mockMeal())
}