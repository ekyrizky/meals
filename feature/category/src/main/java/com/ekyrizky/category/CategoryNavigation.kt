package com.ekyrizky.category

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val CATEGORY_ROUTE = "category_route"

fun NavController.navigateToCategory() = navigate(CATEGORY_ROUTE)

fun NavGraphBuilder.categoryScreen(
    onClick: (String) -> Unit
) {
    composable(route = CATEGORY_ROUTE) {
        CategoryListRoute(onClick = onClick)
    }
}