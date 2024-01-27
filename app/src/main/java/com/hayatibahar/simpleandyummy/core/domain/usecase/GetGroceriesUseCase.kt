package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGroceriesUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(): Flow<List<GroceryEntity>> {
        return recipeRepository.getGroceries()
    }
}