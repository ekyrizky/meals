package com.ekyrizky.network.model.mapper

import com.ekyrizky.model.Meal
import com.ekyrizky.network.model.MealResponse
import com.ekyrizky.network.model.MealsResponse

object MealResponseMapper : ResponseMapper<MealResponse?, Meal> {

    override fun asDomain(response: MealResponse?): Meal {
        return Meal(
            id = response?.id.orEmpty(),
            name = response?.name.orEmpty(),
            image = response?.image.orEmpty(),
            origin = response?.origin.orEmpty(),
            instructions = response?.instructions.orEmpty()
        )
    }
}

object MealsResponseMapper : ResponseMapper<MealsResponse?, List<Meal>> {

    override fun asDomain(response: MealsResponse?): List<Meal> {
        return response?.meals?.map { it.asDomain() } ?: emptyList()
    }
}

fun MealResponse?.asDomain(): Meal = MealResponseMapper.asDomain(this)

fun MealsResponse?.asDomain(): List<Meal> = MealsResponseMapper.asDomain(this)