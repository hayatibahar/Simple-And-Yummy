package com.hayatibahar.simpleandyummy.core.network.dto

data class Step(
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)