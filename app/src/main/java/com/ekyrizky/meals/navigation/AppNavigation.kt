package com.ekyrizky.meals.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ekyrizky.category.CATEGORY_ROUTE
import com.ekyrizky.category.categoryScreen
import com.ekyrizky.meal.navigation.mealsScreen
import com.ekyrizky.meal.navigation.navigateToMeal
import com.ekyrizky.meal.navigation.navigateToMealList

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = CATEGORY_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        categoryScreen(
            onClick = navController::navigateToMealList
        )
        mealsScreen(
            onBackClick = navController::popBackStack,
            onClick = navController::navigateToMeal
        )
    }
}