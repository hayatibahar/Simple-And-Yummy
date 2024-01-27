package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import javax.inject.Inject

class UpdateFavoriteStatusUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(recipeId: Int, isFavorite: Boolean) {
        recipeRepository.updateFavoriteStatus(recipeId, isFavorite)
    }
}