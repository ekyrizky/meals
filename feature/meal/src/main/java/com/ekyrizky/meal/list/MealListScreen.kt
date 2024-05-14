package com.ekyrizky.meal.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ekyrizky.designsystem.component.CircularProgress
import com.ekyrizky.designsystem.component.MealsTopAppBar
import com.ekyrizky.designsystem.icon.MealsIcon
import com.ekyrizky.meal.component.MealCard
import com.ekyrizky.navigation.MealsScreen
import com.ekyrizky.navigation.currentComposeNavigator

@Composable
fun MealListRoute(
    modifier: Modifier = Modifier,
    viewModel: MealListViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val category by viewModel.mealCategory.collectAsStateWithLifecycle()

    MealListScreen(
        uiState = uiState,
        category = category,
        modifier = modifier
    )
}

@Composable
private fun MealListScreen(
    uiState: MealListUiState,
    category: String,
    modifier: Modifier = Modifier
) {

    val composeNavigator = currentComposeNavigator

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
                        navigationIcon = MealsIcon.ArrowBack,
                        onNavigationClick = { composeNavigator.navigateUp() }
                    )
                }
            ) { contentPadding ->
                LazyColumn(modifier = Modifier.padding(contentPadding)) {
                    itemsIndexed(uiState.data) { index, meal ->
                        MealCard(
                            meal = meal,
                            index = index
                        ) { id ->
                            composeNavigator.navigate(
                                route = MealsScreen.MealDetail.createRoute(id = id),
                                optionsBuilder = { launchSingleTop = true }
                            )
                        }
                    }
                }
            }
        }
    }
}