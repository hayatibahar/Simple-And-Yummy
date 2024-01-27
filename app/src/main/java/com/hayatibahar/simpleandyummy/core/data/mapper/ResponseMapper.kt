package com.hayatibahar.simpleandyummy.core.data.mapper

import com.hayatibahar.simpleandyummy.core.common.parseSummaryHtml
import com.hayatibahar.simpleandyummy.core.domain.model.Ingredient
import com.hayatibahar.simpleandyummy.core.domain.model.Instruction
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.core.domain.model.RecipeDetail
import com.hayatibahar.simpleandyummy.core.network.dto.AnalyzedInstructions
import com.hayatibahar.simpleandyummy.core.network.dto.ExtendedIngredient
import com.hayatibahar.simpleandyummy.core.network.dto.RecipesResponse
import com.hayatibahar.simpleandyummy.core.network.dto.Result
import retrofit2.Response

fun Response<RecipesResponse>.toRecipeList(): List<Recipe> {
    val body = this.body() ?: throw IllegalArgumentException("Response body is null")
    return body.results?.mapNotNull {
        it?.let {
            Recipe(
                id = it.foodId,
                title = it.title,
                image = it.image,
                readyInMinutes = it.readyInMinutes
            )
        }
    } ?: throw IllegalArgumentException("Results in response body is null")
}


fun Response<Result>.toRecipeDetail(): RecipeDetail {
    val result = this.body() ?: throw IllegalArgumentException("Response body is null")
    return RecipeDetail(
        id = result.foodId,
        title = result.title,
        summary = parseSummaryHtml(result.summary),
        image = result.image,
        readyInMinutes = result.readyInMinutes,
        ingredients = result.extendedIngredients.toListIngredient(),
        vegan = result.vegan,
        vegetarian = result.vegetarian,
        dairyFree = result.dairyFree,
        glutenFree = result.glutenFree,
        cheap = result.cheap,
        veryHealthy = result.veryHealthy,
        instructions = result.analyzedInstructions.toInstructions()
    )
}

private fun List<ExtendedIngredient>.toListIngredient(): List<Ingredient> {
    return this.map {
        Ingredient(
            originalName = it.originalName,
            nameClean = it.nameClean.orEmpty(),
            amount = it.amount,
            unit = it.unit,
            image = it.image
        )
    }
}

private fun List<AnalyzedInstructions>.toInstructions(): List<Instruction> {
    return this.flatMap { it.steps }.map {
        Instruction(
            number = it.number,
            step = it.step
        )
    }
}

