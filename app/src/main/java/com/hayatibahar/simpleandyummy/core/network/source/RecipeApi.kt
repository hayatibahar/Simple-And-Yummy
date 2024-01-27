package com.hayatibahar.simpleandyummy.core.network.source

import com.hayatibahar.simpleandyummy.core.network.dto.RecipesResponse
import com.hayatibahar.simpleandyummy.core.network.dto.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("recipes/complexSearch")
    suspend fun getAllRecipes(
        @Query("number") number: String = "100",
        @Query("cuisine") cuisine: String = "european",
        @Query("addRecipeInformation") addRecipeInformation: String = "true",
    ): Response<RecipesResponse>

    @GET("recipes/complexSearch")
    suspend fun getRecipesWithSearch(
        @Query("number") number: String = "10",
        @Query("query") query: String = "",
        @Query("cuisine") cuisine: String = "",
        @Query("type") type: String = "",
        @Query("diet") diet: String = "",
        @Query("addRecipeInformation") addRecipeInformation: String = "true",
    ): Response<RecipesResponse>

    @GET("recipes/{id}/information")
    suspend fun getRecipeDetail(
        @Path("id") id: String = "1646939",
    ): Response<Result>

}