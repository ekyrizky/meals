package com.ekyrizky.meal.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
fun MealListRoute(
    modifier: Modifier = Modifier,
    viewModel: MealListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onClick: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val category = viewModel.mealCategory

    MealListScreen(
        category = category,
        uiState = uiState,
        onBackClick = onBackClick,
        onClick = onClick
    )
}

@Composable
private fun MealListScreen(
    category: String,
    uiState: MealListUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onClick: (String) -> Unit
) {

    when (uiState) {
        is MealListUiState.Error -> {
            Text(
                text = uiState.message ?: "Unknown Error",
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }

        is MealListUiState.Loading -> CircularProgress()

        is MealListUiState.Success -> {
            Scaffold(
                modifier = modifier.fillMaxSize(),
                topBar = {
                    MealsTopAppBar(
                        title = category,
                        onNavigationClick = onBackClick
                    )
                }
            ) { contentPadding ->
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.padding(contentPadding)
                ) {
                    items(uiState.data) { meal ->
                        CategoryCard(
                            imageUrl = meal.image,
                            title = meal.name
                        ) {
                            onClick(meal.id)
                        }
                    }
                }
            }
        }
    }
}