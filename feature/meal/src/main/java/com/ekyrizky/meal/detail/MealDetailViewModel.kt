package com.ekyrizky.meal.detail

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
class MealDetailViewModel @Inject constructor(
    mealRepository: MealRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val mealID = savedStateHandle.getStateFlow<String>("id", "")

    val uiState: StateFlow<MealDetailUiState> = mealRepository.fetchMeal(mealID.value)
        .map { MealDetailUiState.Success(it) }
        .catch { MealDetailUiState.Error(it.message.toString()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MealDetailUiState.Loading,
        )
}