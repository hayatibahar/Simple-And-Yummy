package com.hayatibahar.simpleandyummy.core.domain.usecase

import com.hayatibahar.simpleandyummy.core.common.ResponseState
import com.hayatibahar.simpleandyummy.core.domain.model.RecipeDetail
import com.hayatibahar.simpleandyummy.core.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(id: String): Flow<ResponseState<RecipeDetail>> {
        return recipeRepository.getRecipeDetail(id)
    }
}