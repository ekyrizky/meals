package com.ekyrizky.database.entity.mapper

import com.ekyrizky.database.entity.CategoryEntity
import com.ekyrizky.model.Category

object CategoryEntityMapper : EntityMapper<List<Category>, List<CategoryEntity>> {

    override fun asEntity(domain: List<Category>): List<CategoryEntity> {
        return domain.map { category ->
            CategoryEntity(
                name = category.name,
                image = category.image,
                description = category.description
            )
        }
    }

    override fun asDomain(entity: List<CategoryEntity>): List<Category> {
        return entity.map { category ->
            Category(
                name = category.name,
                image = category.image,
                description = category.description
            )
        }
    }
}

fun List<Category>.asEntity(): List<CategoryEntity> = CategoryEntityMapper.asEntity(this)

fun List<CategoryEntity>.asDomain(): List<Category> = CategoryEntityMapper.asDomain(this)