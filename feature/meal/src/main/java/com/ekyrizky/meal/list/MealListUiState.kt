package com.ekyrizky.meal.list

import com.ekyrizky.model.Meal

sealed interface MealListUiState {

    data class Success(val data: List<Meal>) : MealListUiState

    data object Loading : MealListUiState

    data class Error(val message: String?) : MealListUiState
}