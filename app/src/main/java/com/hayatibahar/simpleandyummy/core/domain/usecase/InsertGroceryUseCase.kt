package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import javax.inject.Inject

class InsertGroceryUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(grocery: GroceryEntity) {
        recipeRepository.insertGrocery(grocery)
    }
}