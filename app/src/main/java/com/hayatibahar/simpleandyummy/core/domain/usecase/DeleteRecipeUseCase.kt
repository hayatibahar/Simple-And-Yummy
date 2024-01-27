package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(recipe: Recipe) {
        recipeRepository.deleteRecipe(recipe)
    }
}