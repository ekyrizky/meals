package com.ekyrizky.data.repository.category

import com.ekyrizky.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun fetchCategories(): Flow<List<Category>>
}