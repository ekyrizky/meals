package com.ekyrizky.category

import com.ekyrizky.model.Category

sealed interface CategoryUiState {

    data class Success(val data: List<Category>) : CategoryUiState

    data object Loading : CategoryUiState

    data class Error(val message: String?) : CategoryUiState
}
