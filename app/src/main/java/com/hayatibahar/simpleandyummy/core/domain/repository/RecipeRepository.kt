package com.hayatibahar.simpleandyummy.core.domain.repository

import com.hayatibahar.simpleandyummy.core.common.ResponseState
import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.core.domain.model.Ingredient
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.core.domain.model.RecipeDetail
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getAllRecipesFromRemote(): Flow<ResponseState<List<Recipe>>>

    suspend fun getRecipesWithSearch(
        query: String,
        cuisine: String,
        type: String,
        diet: String,
    ): Flow<ResponseState<List<Recipe>>>

    suspend fun getRecipeDetail(id: String): Flow<ResponseState<RecipeDetail>>


    suspend fun insertRecipes(recipes: List<Recipe>)

    suspend fun insertRecipe(recipe: Recipe)

    suspend fun deleteRecipe(recipe: Recipe)

    suspend fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean)

    suspend fun isRecipeFavorite(recipeId: Int): Flow<Boolean>

    suspend fun getAllRecipesFromLocal(): Flow<ResponseState<List<Recipe>>>

    suspend fun getAllFavoriteRecipesFromLocal(): Flow<List<Recipe>>


    suspend fun insertIngredients(ingredients: List<Ingredient>)

    suspend fun insertGrocery(groceryEntity: GroceryEntity)

    suspend fun deleteGrocery(groceryEntity: GroceryEntity)

    suspend fun deleteCheckedGroceries()

    suspend fun deleteGroceries()

    suspend fun updateGroceryCheckStatus(id: Int, isChecked: Boolean)

    suspend fun getGroceries(): Flow<List<GroceryEntity>>

    suspend fun getAllRecipes(): Flow<ResponseState<List<Recipe>>>

    suspend fun saveDarkModeState(isEnabled: Boolean)
    fun getDarkModeState(): Flow<Boolean>
}