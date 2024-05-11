package com.ekyrizky.data

import app.cash.turbine.test
import com.ekyrizky.data.repository.category.CategoryRepositoryImpl
import com.ekyrizky.data.util.MockUtil.mockCategoryListResponse
import com.ekyrizky.database.dao.CategoryDao
import com.ekyrizky.database.entity.mapper.asEntity
import com.ekyrizky.network.service.MealClient
import com.ekyrizky.network.service.MealService
import com.ekyrizky.test.MainCoroutinesRule
import com.ekyrizky.test.MockData.mockCategoryList
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.responseOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import retrofit2.Response

class CategoryRepositoryTest {

    private lateinit var repository: CategoryRepositoryImpl
    private lateinit var client: MealClient
    private val service: MealService = mock()
    private val dao: CategoryDao = mock()


    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        client = MealClient(service)
        repository = CategoryRepositoryImpl(client, dao, coroutinesRule.testDispatcher)
    }

    @Test
    fun `fetch categories from network test`() = runTest {
        val mockResponse = mockCategoryListResponse()
        val mockData = mockCategoryList()
        whenever(dao.getCategories()).thenReturn(emptyList())
        whenever(service.fetchCategories()).thenReturn(
            ApiResponse.responseOf {
                Response.success(mockResponse)
            }
        )

        repository.fetchCategories().test {
            val actualItem = awaitItem()[0]
            assertEquals(mockData[0].name, actualItem.name)
            assertEquals(mockData[0].image, actualItem.image)
            assertEquals(mockData[0].description, actualItem.description)
            awaitComplete()
        }

        verify(dao, atLeastOnce()).getCategories()
        verify(service, atLeastOnce()).fetchCategories()
        verify(dao).insertCategories(mockData.asEntity())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `fetch categories from database test`() = runTest {
        val mockResponse = mockCategoryListResponse()
        val mockData = mockCategoryList()
        whenever(dao.getCategories()).thenReturn(mockData.asEntity())
        whenever(service.fetchCategories()).thenReturn(
            ApiResponse.responseOf {
                Response.success(mockResponse)
            }
        )

        repository.fetchCategories().test {
            val actualItem = awaitItem()[0]
            assertEquals(mockData[0].name, actualItem.name)
            assertEquals(mockData[0].image, actualItem.image)
            assertEquals(mockData[0].description, actualItem.description)
            awaitComplete()
        }

        verify(dao, atLeastOnce()).getCategories()
        verifyNoMoreInteractions(service)
    }
}