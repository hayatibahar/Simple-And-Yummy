package com.hayatibahar.simpleandyummy.core.database.source

import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.core.database.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val groceryDao: GroceryDao,
    private val dataStoreManager: DataStoreManager,
) : LocalDataSource {
    override suspend fun insertRecipes(recipeEntities: List<RecipeEntity>) {
        recipeDao.insertRecipes(recipeEntities)
    }

    override suspend fun insertRecipe(recipeEntity: RecipeEntity) {
        recipeDao.insertRecipe(recipeEntity)
    }

    override suspend fun deleteRecipe(recipeEntity: RecipeEntity) {
        recipeDao.deleteRecipe(recipeEntity)
    }

    override suspend fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean) {
        recipeDao.updateFavoriteStatus(recipeId, isFavorite)
    }

    override suspend fun isRecipeFavorite(recipeId: Int): Flow<Boolean> {
        return recipeDao.isRecipeFavorite(recipeId)
    }

    override suspend fun getAllRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun getAllFavoriteRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getAllFavoriteRecipes()
    }

    override suspend fun insertGroceries(groceries: List<GroceryEntity>) {
        groceryDao.insertGroceries(groceries)
    }

    override suspend fun insertGrocery(groceryEntity: GroceryEntity) {
        groceryDao.insertGrocery(groceryEntity)
    }

    override suspend fun deleteGrocery(groceryEntity: GroceryEntity) {
        groceryDao.deleteGrocery(groceryEntity)
    }

    override suspend fun deleteCheckedGroceries() {
        groceryDao.deleteCheckedGroceries()
    }

    override suspend fun deleteGroceries() {
        groceryDao.deleteGroceries()
    }

    override suspend fun updateGroceryCheckStatus(id: Int, isChecked: Boolean) {
        groceryDao.updateGroceryCheckStatus(id, isChecked)
    }

    override suspend fun getGroceries(): Flow<List<GroceryEntity>> {
        return groceryDao.getGroceries()
    }

    override suspend fun saveLastRequestTime(time: Long) {
        dataStoreManager.saveLastRequestTime(time)
    }

    override fun getLastRequestTime(): Flow<Long> {
        return dataStoreManager.getLastRequestTime()
    }

    override suspend fun saveDarkModeState(isEnabled: Boolean) {
        dataStoreManager.saveDarkModeState(isEnabled)
    }

    override fun getDarkModeState(): Flow<Boolean> {
        return dataStoreManager.getDarkModeState()
    }
}