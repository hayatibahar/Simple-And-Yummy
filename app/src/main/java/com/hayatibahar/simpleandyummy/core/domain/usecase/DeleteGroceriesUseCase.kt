package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import javax.inject.Inject

class DeleteGroceriesUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke() {
        recipeRepository.deleteGroceries()
    }
}