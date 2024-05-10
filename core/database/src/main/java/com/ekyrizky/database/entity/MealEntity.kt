package com.ekyrizky.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MealEntity(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
    val origin: String,
    val instructions: String
)