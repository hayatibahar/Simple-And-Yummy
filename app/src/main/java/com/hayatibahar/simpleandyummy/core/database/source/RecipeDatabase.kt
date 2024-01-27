package com.hayatibahar.simpleandyummy.core.database.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.core.database.entity.RecipeEntity

@Database(entities = [RecipeEntity::class, GroceryEntity::class], version = 2, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun groceryDao(): GroceryDao

}