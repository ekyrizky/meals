package com.ekyrizky.meal.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ekyrizky.meal.detail.MealDetailRoute
import com.ekyrizky.meal.list.MealListRoute
import java.net.URLDecoder
import java.net.URLEncoder

private const val MEAL_CATEGORY_ARG = "mealCategory"
private const val MEAL_ID_ARG = "mealID"
const val MEAL_LIST_ROUTE = "meal_list_route"
const val MEAL_ROUTE = "meal_route"
private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

internal class MealListArgs(val mealCategory: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[MEAL_CATEGORY_ARG]), URL_CHARACTER_ENCODING))
}

internal class MealArgs(val mealID: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[MEAL_ID_ARG]), URL_CHARACTER_ENCODING))
}

fun NavController.navigateToMealList(mealCategory: String) {
    val encodedID = URLEncoder.encode(mealCategory, URL_CHARACTER_ENCODING)
    navigate("$MEAL_LIST_ROUTE/$encodedID") {
        launchSingleTop = true
    }
}

fun NavController.navigateToMeal(mealID: String) {
    val encodedID = URLEncoder.encode(mealID, URL_CHARACTER_ENCODING)
    navigate("$MEAL_ROUTE/$encodedID") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.mealsScreen(
    onBackClick: () -> Unit,
    onClick: (String) -> Unit
) {
    composable(
        route = "$MEAL_LIST_ROUTE/{$MEAL_CATEGORY_ARG}",
        arguments = listOf(
            navArgument(MEAL_CATEGORY_ARG) { type = NavType.StringType }
        )
    ) {
        MealListRoute(
            onBackClick = onBackClick,
            onClick = onClick
        )
    }

    composable(
        route = "$MEAL_ROUTE/{$MEAL_ID_ARG}",
        arguments = listOf(
            navArgument(MEAL_ID_ARG) { type = NavType.StringType }
        )
    ) {
        MealDetailRoute(
            onBackClick = onBackClick
        )
    }
}