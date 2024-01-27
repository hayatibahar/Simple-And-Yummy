package com.hayatibahar.simpleandyummy.core.network.source

import com.hayatibahar.simpleandyummy.core.network.dto.RecipesResponse
import com.hayatibahar.simpleandyummy.core.network.dto.Result
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val recipeApi: RecipeApi) :
    RemoteDataSource {
    override suspend fun getAllRecipes(): Response<RecipesResponse> {
        return recipeApi.getAllRecipes()
    }

    override suspend fun getRecipesWithSearch(
        query: String,
        cuisine: String,
        type: String,
        diet: String,
    ): Response<RecipesResponse> {
        return recipeApi.getRecipesWithSearch(
            query = query,
            cuisine = cuisine,
            type = type,
            diet = diet
        )
    }

    override suspend fun getRecipeDetail(id: String): Response<Result> {
        return recipeApi.getRecipeDetail(id)
    }

}