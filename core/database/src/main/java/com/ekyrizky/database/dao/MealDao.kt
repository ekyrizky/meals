package com.ekyrizky.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekyrizky.database.entity.MealEntity

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealsByCategory(meals: List<MealEntity>)

    @Query("SELECT * FROM MealEntity WHERE id = :id")
    suspend fun getMeal(id: String): MealEntity

    @Query("SELECT * FROM MealEntity WHERE name LIKE '%' || :name || '%'")
    suspend fun getMealsByCategory(name: String): List<MealEntity>
}