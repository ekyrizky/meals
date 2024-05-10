package com.ekyrizky.database

import com.ekyrizky.database.dao.MealDao
import com.ekyrizky.database.entity.mapper.asEntity
import com.ekyrizky.test.MockData.mockMealList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MealDaoTest : LocalDatabase() {

    private lateinit var mealDao: MealDao

    @Before
    fun init() {
        mealDao = db.mealDao()
    }

    @Test
    fun `insert and load meals by category test`() = runBlocking {
        val mockMeals = mockMealList().asEntity()
        mealDao.insertMealsByCategory(mockMeals)

        val loadFromDB = mealDao.getMealsByCategory("Beef")
        assertThat(loadFromDB.toString(), `is`(mockMeals.toString()))
    }

    @Test
    fun `insert and load meal test`() = runBlocking {
        val mockMeals = mockMealList().asEntity()[0]
        mealDao.insertMeal(mockMeals)

        val loadFromDB = mealDao.getMeal(id = "52874")
        assertThat(loadFromDB.toString(), `is`(mockMeals.toString()))
    }
}