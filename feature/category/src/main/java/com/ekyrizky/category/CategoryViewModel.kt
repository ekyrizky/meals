package com.ekyrizky.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekyrizky.data.repository.category.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    categoryRepository: CategoryRepository
) : ViewModel() {

    val uiState: StateFlow<CategoryUiState> = categoryRepository.fetchCategories()
        .map { CategoryUiState.Success(it) }
        .catch { CategoryUiState.Error(it.message) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CategoryUiState.Loading,
        )
}