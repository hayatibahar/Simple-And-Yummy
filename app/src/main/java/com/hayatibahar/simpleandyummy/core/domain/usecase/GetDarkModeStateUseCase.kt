package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDarkModeStateUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(): Flow<Boolean> {
        return recipeRepository.getDarkModeState()
    }
}