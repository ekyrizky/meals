package com.ekyrizky.test

import com.ekyrizky.model.Category
import com.ekyrizky.model.Meal

object MockData {

    fun mockCategory() = Category(
        name = "Beef",
        image = "https://www.themealdb.com/images/category/beef.png",
        description = "Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]"
    )

    fun mockCategoryList() = listOf(mockCategory())

    fun mockMeal() = Meal(
        id = "52874",
        name = "Beef and Mustard Pie",
        image = "https://www.themealdb.com/images/media/meals/sytuqu1511553755.jpg",
        origin = "",
        instructions = ""
    )

    fun mockMealList() = listOf(mockMeal())
}