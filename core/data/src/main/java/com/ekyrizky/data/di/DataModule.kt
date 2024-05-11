package com.ekyrizky.data.di

import com.ekyrizky.data.repository.category.CategoryRepository
import com.ekyrizky.data.repository.category.CategoryRepositoryImpl
import com.ekyrizky.data.repository.meal.MealRepository
import com.ekyrizky.data.repository.meal.MealRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsCategoryRepository(categoryRepository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindsMealRepository(mealRepository: MealRepositoryImpl): MealRepository
}