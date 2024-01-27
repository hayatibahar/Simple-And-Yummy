package com.hayatibahar.simpleandyummy.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hayatibahar.simpleandyummy.core.common.DbConstants.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val isFavorite: Boolean
)