package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteRecipes @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(): Flow<List<Recipe>> {
        return recipeRepository.getAllFavoriteRecipesFromLocal()
    }
}