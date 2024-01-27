package com.hayatibahar.simpleandyummy.core.network.source

import com.hayatibahar.simpleandyummy.core.network.dto.RecipesResponse
import com.hayatibahar.simpleandyummy.core.network.dto.Result
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getAllRecipes(): Response<RecipesResponse>

    suspend fun getRecipesWithSearch(
        query: String,
        cuisine: String,
        type: String,
        diet: String,
    ): Response<RecipesResponse>

    suspend fun getRecipeDetail(id: String): Response<Result>

}