package com.hayatibahar.simpleandyummy.core.database.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hayatibahar.simpleandyummy.core.database.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipes(recipeEntities: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    @Delete
    suspend fun deleteRecipe(recipeEntity: RecipeEntity)

    @Query("UPDATE RECIPES_TABLE SET isFavorite = :isFavorite WHERE id= :recipeId")
    suspend fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean)

    @Query("SELECT isFavorite FROM RECIPES_TABLE WHERE id = :recipeId")
    fun isRecipeFavorite(recipeId: Int): Flow<Boolean>

    @Query("SELECT * FROM RECIPES_TABLE")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM RECIPES_TABLE where isFavorite == 1")
    fun getAllFavoriteRecipes(): Flow<List<RecipeEntity>>

}