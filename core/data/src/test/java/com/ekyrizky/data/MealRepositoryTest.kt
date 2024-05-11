package com.ekyrizky.data

import app.cash.turbine.test
import com.ekyrizky.data.repository.meal.MealRepositoryImpl
import com.ekyrizky.data.util.MockUtil.mockMealSResponse
import com.ekyrizky.database.dao.MealDao
import com.ekyrizky.database.entity.mapper.asEntity
import com.ekyrizky.network.service.MealClient
import com.ekyrizky.network.service.MealService
import com.ekyrizky.test.MainCoroutinesRule
import com.ekyrizky.test.MockData.mockMealList
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.responseOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import retrofit2.Response

class MealRepositoryTest {

    private lateinit var repository: MealRepositoryImpl
    private lateinit var client: MealClient
    private val service: MealService = mock()
    private val dao: MealDao = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        client = MealClient(service)
        repository = MealRepositoryImpl(client, dao, coroutinesRule.testDispatcher)
    }

    @Test
    fun `fetch meals from network test`() = runTest {
        val mockResponse = mockMealSResponse()
        val mockData = mockMealList()
        whenever(dao.getMealsByCategory("Beef")).thenReturn(emptyList())
        whenever(service.fetchMealsByCategory("Beef")).thenReturn(
            ApiResponse.responseOf {
                Response.success(mockResponse)
            }
        )

        repository.fetchMealsByCategory("Beef").test {
            val actualItem = awaitItem()[0]
            Assert.assertEquals(mockData[0].id, actualItem.id)
            Assert.assertEquals(mockData[0].name, actualItem.name)
            Assert.assertEquals(mockData[0].image, actualItem.image)
            awaitComplete()
        }

        verify(dao, atLeastOnce()).getMealsByCategory("Beef")
        verify(service, atLeastOnce()).fetchMealsByCategory("Beef")
        verify(dao).insertMeals(mockData.asEntity())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `fetch meals from database test`() = runTest {
        val mockResponse = mockMealSResponse()
        val mockData = mockMealList()
        whenever(dao.getMealsByCategory("Beef")).thenReturn(mockData.asEntity())
        whenever(service.fetchMealsByCategory("Beef")).thenReturn(
            ApiResponse.responseOf {
                Response.success(mockResponse)
            }
        )

        repository.fetchMealsByCategory("Beef").test {
            val actualItem = awaitItem()[0]
            Assert.assertEquals(mockData[0].id, actualItem.id)
            Assert.assertEquals(mockData[0].name, actualItem.name)
            Assert.assertEquals(mockData[0].image, actualItem.image)
            awaitComplete()
        }

        verify(dao, atLeastOnce()).getMealsByCategory("Beef")
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `fetch meal from network test`() = runTest {
        val mockResponse = mockMealSResponse()
        val mockData = mockMealList()[0]
        whenever(dao.getMeal("52874")).thenReturn(null)
        whenever(service.fetchMeal("52874")).thenReturn(
            ApiResponse.responseOf {
                Response.success(mockResponse)
            }
        )

        repository.fetchMeal("52874").test {
            val actualItem = awaitItem()
            Assert.assertEquals(mockData.id, actualItem.id)
            Assert.assertEquals(mockData.name, actualItem.name)
            Assert.assertEquals(mockData.image, actualItem.image)
            Assert.assertEquals(mockData.origin, actualItem.origin)
            Assert.assertEquals(mockData.instructions, actualItem.instructions)
            awaitComplete()
        }

        verify(dao, atLeastOnce()).getMeal("52874")
        verify(service, atLeastOnce()).fetchMeal("52874")
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `fetch meal from database test`() = runTest {
        val mockResponse = mockMealSResponse()
        val mockData = mockMealList()[0]
        whenever(dao.getMeal("52874")).thenReturn(mockData.asEntity())
        whenever(service.fetchMeal("52874")).thenReturn(
            ApiResponse.responseOf {
                Response.success(mockResponse)
            }
        )

        repository.fetchMeal("52874").test {
            val actualItem = awaitItem()
            Assert.assertEquals(mockData.id, actualItem.id)
            Assert.assertEquals(mockData.name, actualItem.name)
            Assert.assertEquals(mockData.image, actualItem.image)
            Assert.assertEquals(mockData.origin, actualItem.origin)
            Assert.assertEquals(mockData.instructions, actualItem.instructions)
            awaitComplete()
        }

        verify(dao, atLeastOnce()).getMeal("52874")
        verifyNoMoreInteractions(service)
    }
}