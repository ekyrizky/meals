package com.ekyrizky.data.repository.meal

import com.ekyrizky.database.dao.MealDao
import com.ekyrizky.database.entity.mapper.asDomain
import com.ekyrizky.database.entity.mapper.asEntity
import com.ekyrizky.model.Meal
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

class MealRepositoryImpl @Inject constructor(
    private val client: MealClient,
    private val dao: MealDao,
    @Dispatcher(MealsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : MealRepository {
    override fun fetchMealsByCategory(name: String): Flow<List<Meal>> {
        return flow {
            var meals = dao.getMealsByCategory(name).asDomain()
            if (meals.isEmpty()) {
                val response = client.fetchMealsByCategory(name)
                response.suspendOnSuccess {
                    meals = data.asDomain()
                    dao.insertMeals(meals.asEntity())
                    emit(meals)
                }
            } else {
                emit(meals)
            }
        }.flowOn(ioDispatcher)
    }

    override fun fetchMeal(id: String): Flow<Meal> {
        return flow {
            val meal = dao.getMeal(id)?.asDomain()
            if (meal == null) {
                val response = client.fetchMeal(id)
                response.suspendOnSuccess {
                    emit(data.asDomain().first())
                }
            } else {
                emit(meal)
            }
        }.flowOn(ioDispatcher)
    }
}