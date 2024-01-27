package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import javax.inject.Inject

class SaveDarkModeStateUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(isEnabled: Boolean) {
        recipeRepository.saveDarkModeState(isEnabled)
    }
}