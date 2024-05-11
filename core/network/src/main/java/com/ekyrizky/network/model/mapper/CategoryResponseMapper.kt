package com.ekyrizky.network.model.mapper

import com.ekyrizky.model.Category
import com.ekyrizky.network.model.CategoriesResponse

object CategoryResponseMapper : ResponseMapper<CategoriesResponse, List<Category>> {
    override fun asDomain(response: CategoriesResponse): List<Category> {
        return response.categories?.map { category ->
            Category(
                name = category?.name.orEmpty(),
                image = category?.image.orEmpty(),
                description = category?.description.orEmpty()
            )
        } ?: emptyList()
    }
}

fun CategoriesResponse.asDomain(): List<Category> = CategoryResponseMapper.asDomain(this)