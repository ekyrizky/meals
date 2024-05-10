package com.ekyrizky.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey val name: String,
    val image: String,
    val description: String
)