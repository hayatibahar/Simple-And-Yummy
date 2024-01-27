package com.hayatibahar.simpleandyummy.core.domain.model

data class RecipeDetail(
    val id: Int,
    val title: String,
    val summary: String,
    val image: String,
    val readyInMinutes: Int,
    val ingredients: List<Ingredient>,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val dairyFree: Boolean,
    val glutenFree: Boolean,
    val cheap: Boolean,
    val veryHealthy: Boolean,
    val instructions: List<Instruction>,
    val isFavorite: Boolean = false,
    val onFavorite: () -> Unit = {},
)