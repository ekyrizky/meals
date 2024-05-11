package com.ekyrizky.meal.detail

import com.ekyrizky.model.Meal

sealed interface MealDetailUiState {

    data class Success(val data: Meal) : MealDetailUiState

    data object Loading : MealDetailUiState

    data class Error(val message: String?) : MealDetailUiState
}