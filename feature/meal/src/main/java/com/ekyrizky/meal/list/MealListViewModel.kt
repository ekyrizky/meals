package com.ekyrizky.meal.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekyrizky.data.repository.meal.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    mealRepository: MealRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val mealCategory = savedStateHandle.getStateFlow<String>("category", "")

    val uiState: StateFlow<MealListUiState> = mealRepository.fetchMealsByCategory(mealCategory.value)
        .map { MealListUiState.Success(it) }
        .catch { MealListUiState.Error(it.message.toString()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MealListUiState.Loading,
        )
}