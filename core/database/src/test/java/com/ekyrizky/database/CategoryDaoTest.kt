package com.ekyrizky.database

import com.ekyrizky.database.dao.CategoryDao
import com.ekyrizky.database.entity.mapper.asEntity
import com.ekyrizky.test.MockData.mockCategoryList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CategoryDaoTest : LocalDatabase() {

    private lateinit var categoryDao: CategoryDao

    @Before
    fun init() {
        categoryDao = db.categoryDao()
    }

    @Test
    fun `insert and load categories test`() = runBlocking {
        val mockCategories = mockCategoryList().asEntity()
        categoryDao.insertCategories(mockCategories)

        val loadFromDB = categoryDao.getCategories()
        assertThat(loadFromDB.toString(), `is`(mockCategories.toString()))
    }
}