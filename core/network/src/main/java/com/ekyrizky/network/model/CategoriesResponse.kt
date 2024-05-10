package com.ekyrizky.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResponse(
    @Json(name = "categories")
    val categories: List<MealCategoryResponse?>?
)

@JsonClass(generateAdapter = true)
data class MealCategoryResponse(
    @Json(name = "strCategory")
    val name: String?,
    @Json(name = "strCategoryThumb")
    val image: String?,
    @Json(name = "strCategoryDescription")
    val description: String?
)