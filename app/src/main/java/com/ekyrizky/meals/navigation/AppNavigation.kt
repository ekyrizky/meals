package com.ekyrizky.meals.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ekyrizky.category.CategoryListRoute
import com.ekyrizky.meal.detail.MealDetailRoute
import com.ekyrizky.meal.list.MealListRoute
import com.ekyrizky.navigation.MealsScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MealsScreen.CategoryList.name,
        modifier = modifier
    ) {
        composable(route = MealsScreen.CategoryList.name) {
            CategoryListRoute()
        }
        composable(
            route = MealsScreen.MealList.name,
            arguments = MealsScreen.MealList.navArguments
        ) {
            MealListRoute()
        }

        composable(
            route = MealsScreen.MealDetail.name,
            arguments = MealsScreen.MealDetail.navArguments
        ) {
            MealDetailRoute()
        }
    }
}