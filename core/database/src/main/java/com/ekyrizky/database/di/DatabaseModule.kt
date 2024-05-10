package com.ekyrizky.database.di

import android.app.Application
import androidx.room.Room
import com.ekyrizky.database.MealsDatabase
import com.ekyrizky.database.dao.CategoryDao
import com.ekyrizky.database.dao.MealDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): MealsDatabase {
        return Room
            .databaseBuilder(application, MealsDatabase::class.java, "Meals.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(db: MealsDatabase): CategoryDao = db.categoryDao()

    @Provides
    @Singleton
    fun provideMealsDao(db: MealsDatabase): MealDao = db.mealDao()
}