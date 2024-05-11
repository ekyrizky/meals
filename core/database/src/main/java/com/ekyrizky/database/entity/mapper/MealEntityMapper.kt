package com.ekyrizky.database.entity.mapper

import com.ekyrizky.database.entity.MealEntity
import com.ekyrizky.model.Meal

object MealEntityMapper : EntityMapper<Meal, MealEntity> {

    override fun asEntity(domain: Meal): MealEntity {
        return MealEntity(
            id = domain.id,
            name = domain.name,
            image = domain.image,
            origin = domain.origin,
            instructions = domain.instructions
        )
    }

    override fun asDomain(entity: MealEntity): Meal {
        return Meal(
            id = entity.id,
            name = entity.name,
            image = entity.image,
            origin = entity.origin,
            instructions = entity.instructions
        )
    }
}

object MealsEntityMapper : EntityMapper<List<Meal>, List<MealEntity>> {

    override fun asEntity(domain: List<Meal>): List<MealEntity> {
        return domain.map { it.asEntity() }
    }

    override fun asDomain(entity: List<MealEntity>): List<Meal> {
        return entity.map { it.asDomain() }
    }
}

fun Meal.asEntity(): MealEntity = MealEntityMapper.asEntity(this)

fun MealEntity.asDomain(): Meal = MealEntityMapper.asDomain(this)

fun List<Meal>.asEntity(): List<MealEntity> = MealsEntityMapper.asEntity(this)

fun List<MealEntity>.asDomain(): List<Meal> = MealsEntityMapper.asDomain(this)