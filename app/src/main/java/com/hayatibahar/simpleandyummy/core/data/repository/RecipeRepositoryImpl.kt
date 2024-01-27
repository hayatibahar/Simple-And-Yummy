package com.hayatibahar.simpleandyummy.core.data.repository

import com.hayatibahar.simpleandyummy.core.common.ResponseState
import com.hayatibahar.simpleandyummy.core.common.mapTo
import com.hayatibahar.simpleandyummy.core.data.mapper.toGroceryEntityList
import com.hayatibahar.simpleandyummy.core.data.mapper.toRecipeDetail
import com.hayatibahar.simpleandyummy.core.data.mapper.toRecipeEntity
import com.hayatibahar.simpleandyummy.core.data.mapper.toRecipeEntityList
import com.hayatibahar.simpleandyummy.core.data.mapper.toRecipeList
import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.core.database.source.LocalDataSource
import com.hayatibahar.simpleandyummy.core.domain.model.Ingredient
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.core.domain.model.RecipeDetail
import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import com.hayatibahar.simpleandyummy.core.network.interceptor.NetworkUnavailableException
import com.hayatibahar.simpleandyummy.core.network.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : RecipeRepository {
    override suspend fun getAllRecipesFromRemote(): Flow<ResponseState<List<Recipe>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = remoteDataSource.getAllRecipes()
            emit(ResponseState.Success(response.mapTo { it.toRecipeList() }))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getRecipesWithSearch(
        query: String,
        cuisine: String,
        type: String,
        diet: String,
    ): Flow<ResponseState<List<Recipe>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = remoteDataSource.getRecipesWithSearch(query, cuisine, type, diet)
            emit(ResponseState.Success(response.mapTo { it.toRecipeList() }))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun getRecipeDetail(id: String): Flow<ResponseState<RecipeDetail>> {
        return flow {
            emit(ResponseState.Loading)
            val response = remoteDataSource.getRecipeDetail(id)
            emit(ResponseState.Success(response.mapTo { it.toRecipeDetail() }))
        }.catch {
            emit(ResponseState.Error(it.message.orEmpty()))
        }
    }

    override suspend fun insertRecipes(recipes: List<Recipe>) {
        localDataSource.insertRecipes(recipes.toRecipeEntityList())
    }

    override suspend fun insertRecipe(recipe: Recipe) {
        localDataSource.insertRecipe(recipe.toRecipeEntity())
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        localDataSource.deleteRecipe(recipe.toRecipeEntity())
    }

    override suspend fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean) {
        localDataSource.updateFavoriteStatus(recipeId, isFavorite)
    }

    override suspend fun isRecipeFavorite(recipeId: Int): Flow<Boolean> {
        return localDataSource.isRecipeFavorite(recipeId)
    }

    override suspend fun getAllRecipesFromLocal(): Flow<ResponseState<List<Recipe>>> {
        return flow {
            emit(ResponseState.Loading)
            try {
                val recipes = localDataSource.getAllRecipes().map { it.toRecipeList() }.first()
                emit(ResponseState.Success(recipes))
            } catch (exception: Exception) {
                emit(ResponseState.Error(exception.message.orEmpty()))
            }
        }
    }

    override suspend fun getAllFavoriteRecipesFromLocal(): Flow<List<Recipe>> {
        return localDataSource.getAllFavoriteRecipes().map {
            it.toRecipeList()
        }
    }

    override suspend fun insertIngredients(ingredients: List<Ingredient>) {
        localDataSource.insertGroceries(ingredients.toGroceryEntityList())
    }

    override suspend fun insertGrocery(groceryEntity: GroceryEntity) {
        localDataSource.insertGrocery(groceryEntity)
    }

    override suspend fun deleteGrocery(groceryEntity: GroceryEntity) {
        localDataSource.deleteGrocery(groceryEntity)
    }

    override suspend fun deleteCheckedGroceries() {
        localDataSource.deleteCheckedGroceries()
    }

    override suspend fun deleteGroceries() {
        localDataSource.deleteGroceries()
    }

    override suspend fun updateGroceryCheckStatus(id: Int, isChecked: Boolean) {
        localDataSource.updateGroceryCheckStatus(id, isChecked)
    }

    override suspend fun getGroceries(): Flow<List<GroceryEntity>> {
        return localDataSource.getGroceries()
    }

    override suspend fun getAllRecipes(): Flow<ResponseState<List<Recipe>>> {
        return flow {
            emit(ResponseState.Loading)
            try {
                val lastRequestTime = localDataSource.getLastRequestTime().first()
                if (shouldFetchNewData(lastRequestTime)) {
                    val response = remoteDataSource.getAllRecipes().mapTo { it.toRecipeList() }
                    emit(ResponseState.Success(response))
                    localDataSource.insertRecipes(response.toRecipeEntityList())
                    localDataSource.saveLastRequestTime(System.currentTimeMillis())
                } else {
                    val recipes =
                        localDataSource.getAllRecipes().first().mapTo { it.toRecipeList() }
                    emit(ResponseState.Success(recipes))
                }
            } catch (exception: NetworkUnavailableException) {
                val recipes = localDataSource.getAllRecipes().first().mapTo { it.toRecipeList() }
                emit(ResponseState.Success(recipes))
            } catch (exception: Exception) {
                emit(ResponseState.Error(exception.message.orEmpty()))
            }
        }
    }

    override suspend fun saveDarkModeState(isEnabled: Boolean) {
        localDataSource.saveDarkModeState(isEnabled)
    }

    override fun getDarkModeState(): Flow<Boolean> {
        return localDataSource.getDarkModeState()
    }

    private fun shouldFetchNewData(lastRequestTime: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        val oneDayInMillis = 24 * 60 * 60 * 1000
        return currentTime - lastRequestTime >= oneDayInMillis
    }

}