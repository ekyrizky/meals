package com.ekyrizky.navigation

import android.util.Log
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.navArgument

sealed class MealsScreen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    val name: String = route.appendArguments(navArguments)

    data object CategoryList : MealsScreen("category")
    data object MealList : MealsScreen(
        route = "meal_list",
        navArguments = listOf(
            navArgument("category") {
                type = StringType
                nullable = false
            }
        )
    ) {
        fun createRoute(category: String): String {
            Log.e("log__","category = $category")
            Log.e("log__","navArguments= ${navArguments}")
            Log.e("log__","navArguments first= ${navArguments.first()}")
            Log.e("log__","navArguments name= ${navArguments.first().name}")
            Log.e("log__","navArguments replace= ${name.replace("{${navArguments.first().name}}", category)}")
            return name.replace("{${navArguments.first().name}}", category)
        }

    }

    data object MealDetail : MealsScreen(
        route = "meal_detail",
        navArguments = listOf(
            navArgument("id") {
                type = StringType
                nullable = false
            }
        )
    ) {
        fun createRoute(id: String) =
            name.replace("{${navArguments.first().name}}", id)
    }
}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
    val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
        .orEmpty()
    val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
        .orEmpty()
    return "$this$mandatoryArguments$optionalArguments"
}
