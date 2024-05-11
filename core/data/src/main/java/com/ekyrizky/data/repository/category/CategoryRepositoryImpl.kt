package com.ekyrizky.data.repository.category

import com.ekyrizky.database.dao.CategoryDao
import com.ekyrizky.database.entity.mapper.asDomain
import com.ekyrizky.database.entity.mapper.asEntity
import com.ekyrizky.model.Category
import com.ekyrizky.network.di.Dispatcher
import com.ekyrizky.network.di.MealsAppDispatchers
import com.ekyrizky.network.model.mapper.asDomain
import com.ekyrizky.network.service.MealClient
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val client: MealClient,
    private val dao: CategoryDao,
    @Dispatcher(MealsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : CategoryRepository {
    override fun fetchCategories(): Flow<List<Category>> {
        return flow {
            var categories = dao.getCategories().asDomain()
            if (categories.isEmpty()) {
                val response = client.fetchCategories()
                response.suspendOnSuccess {
                    categories = data.asDomain()
                    dao.insertCategories(categories.asEntity())
                    emit(categories)
                }
            } else {
                emit(categories)
            }
        }.flowOn(ioDispatcher)
    }
}