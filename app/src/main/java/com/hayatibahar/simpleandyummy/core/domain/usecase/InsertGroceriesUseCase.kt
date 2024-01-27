package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.domain.model.Ingredient
import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import javax.inject.Inject

class InsertGroceriesUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(ingredient: List<Ingredient>) {
        recipeRepository.insertIngredients(ingredient)
    }
}