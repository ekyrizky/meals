package com.ekyrizky.database.entity.mapper

import com.ekyrizky.database.entity.MealEntity
import com.ekyrizky.model.Meal

object MealEntityMapper : EntityMapper<List<Meal>, List<MealEntity>> {

    override fun asEntity(domain: List<Meal>): List<MealEntity> {
        return domain.map { meal ->
            MealEntity(
                id = meal.id,
                name = meal.name,
                image = meal.image,
                origin = meal.origin,
                instructions = meal.instructions
            )
        }
    }

    override fun asDomain(entity: List<MealEntity>): List<Meal> {
        return entity.map { meal ->
            Meal(
                id = meal.id,
                name = meal.name,
                image = meal.image,
                origin = meal.origin,
                instructions = meal.instructions
            )
        }
    }
}

fun List<Meal>.asEntity(): List<MealEntity> = MealEntityMapper.asEntity(this)

fun List<MealEntity>.asDomain(): List<Meal> = MealEntityMapper.asDomain(this)