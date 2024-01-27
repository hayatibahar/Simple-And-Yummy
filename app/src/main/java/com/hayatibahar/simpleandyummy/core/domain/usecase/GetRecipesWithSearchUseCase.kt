package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.common.ResponseState
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesWithSearchUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(
        query: String,
        cuisine: String,
        type: String,
        diet: String,
    ): Flow<ResponseState<List<Recipe>>> {
        return recipeRepository.getRecipesWithSearch(query, cuisine, type, diet)
    }
}