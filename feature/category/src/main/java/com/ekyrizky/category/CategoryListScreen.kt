package com.ekyrizky.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ekyrizky.category.component.CategoryCard
import com.ekyrizky.designsystem.component.CircularProgress
import com.ekyrizky.designsystem.component.MealsTopAppBar
import com.ekyrizky.navigation.MealsScreen
import com.ekyrizky.navigation.currentComposeNavigator
import com.ekyrizky.designsystem.R as systemR

@Composable
fun CategoryListRoute(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CategoryListScreen(
        uiState = uiState
    )
}

@Composable
private fun CategoryListScreen(
    uiState: CategoryUiState,
    modifier: Modifier = Modifier
) {
    val composeNavigator = currentComposeNavigator

    when (uiState) {
        is CategoryUiState.Error -> {
            Text(
                text = uiState.message ?: "Unknown Error",
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }

        is CategoryUiState.Loading -> CircularProgress()

        is CategoryUiState.Success -> {
            Scaffold(
                modifier = modifier.fillMaxSize(),
                topBar = {
                    MealsTopAppBar(title = stringResource(id = systemR.string.app_name))
                }
            ) { contentPadding ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(contentPadding)
                ) {
                    items(uiState.data) { category ->
                        CategoryCard(
                            imageUrl = category.image,
                            title = category.name
                        ) {
                            composeNavigator.navigate(MealsScreen.MealList.createRoute(category = category.name))
                        }
                    }
                }
            }
        }
    }
}