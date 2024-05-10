package com.ekyrizky.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealsResponse(
    @Json(name = "meals")
    val meals: List<MealResponse?>?
)

@JsonClass(generateAdapter = true)
data class MealResponse(
    @Json(name = "idMeal")
    val id: String?,
    @Json(name = "strMeal")
    val name: String?,
    @Json(name = "strMealThumb")
    val image: String?,
    @Json(name = "strArea")
    val origin: String?,
    @Json(name = "strInstructions")
    val instructions: String?
)