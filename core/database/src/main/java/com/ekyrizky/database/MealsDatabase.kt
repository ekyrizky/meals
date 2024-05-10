package com.ekyrizky.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ekyrizky.database.dao.CategoryDao
import com.ekyrizky.database.dao.MealDao
import com.ekyrizky.database.entity.CategoryEntity
import com.ekyrizky.database.entity.MealEntity

@Database(
    entities = [CategoryEntity::class, MealEntity::class],
    version = 1,
    exportSchema = true
)
abstract class MealsDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun mealDao(): MealDao
}