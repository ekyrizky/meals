package com.ekyrizky.meal.detail

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ekyrizky.designsystem.component.CategoryCard
import com.ekyrizky.designsystem.component.CircularProgress
import com.ekyrizky.designsystem.component.MealsTopAppBar

@Composable
fun MealDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: MealDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val id = viewModel.mealID

    MealDetailScreen(
        id = id,
        uiState = uiState,
        onBackClick = onBackClick
    )
}

@Composable
private fun MealDetailScreen(
    id: String,
    uiState: MealDetailUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    when (uiState) {
        is MealDetailUiState.Error -> {
            Text(
                text = uiState.message ?: "Unknown Error",
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }

        is MealDetailUiState.Loading -> CircularProgress()

        is MealDetailUiState.Success -> {
            Scaffold(
                modifier = modifier.fillMaxSize(),
                topBar = {
                    MealsTopAppBar(
                        title = id,
                        onNavigationClick = onBackClick
                    )
                }
            ) { contentPadding ->
                CategoryCard(
                    imageUrl = uiState.data.image,
                    title = uiState.data.name,
                    modifier = modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                        .wrapContentSize(),
                    onClick = {}
                )
            }
        }
    }
}