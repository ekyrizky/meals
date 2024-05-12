package com.ekyrizky.meals.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.ekyrizky.designsystem.theme.MealsTheme
import com.ekyrizky.meals.navigation.AppNavigation
import com.ekyrizky.navigation.AppComposeNavigator

@Composable
fun MealsMain(
    composeNavigator: AppComposeNavigator
) {
    MealsTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        AppNavigation(navController = navHostController)
    }
}