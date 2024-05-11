package com.ekyrizky.data.repository.meal

import com.ekyrizky.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun fetchMealsByCategory(name: String): Flow<List<Meal>>

    fun fetchMeal(id: String): Flow<Meal>
}