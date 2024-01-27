package com.hayatibahar.simpleandyummy.core.domain.model

data class Ingredient(
    val originalName: String,
    val nameClean: String,
    val amount: Double,
    val unit: String,
    val image: String,
)