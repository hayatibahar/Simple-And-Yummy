package com.hayatibahar.simpleandyummy.core.database.source

import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.core.database.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertRecipes(recipeEntities: List<RecipeEntity>)

    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    suspend fun deleteRecipe(recipeEntity: RecipeEntity)

    suspend fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean)

    suspend fun isRecipeFavorite(recipeId: Int): Flow<Boolean>

    suspend fun getAllRecipes(): Flow<List<RecipeEntity>>

    suspend fun getAllFavoriteRecipes(): Flow<List<RecipeEntity>>


    suspend fun insertGroceries(groceries: List<GroceryEntity>)

    suspend fun insertGrocery(groceryEntity: GroceryEntity)

    suspend fun deleteGrocery(groceryEntity: GroceryEntity)

    suspend fun deleteCheckedGroceries()

    suspend fun deleteGroceries()

    suspend fun updateGroceryCheckStatus(id: Int, isChecked: Boolean)

    suspend fun getGroceries(): Flow<List<GroceryEntity>>

    suspend fun saveLastRequestTime(time: Long)
    fun getLastRequestTime(): Flow<Long>
    suspend fun saveDarkModeState(isEnabled: Boolean)
    fun getDarkModeState(): Flow<Boolean>

}