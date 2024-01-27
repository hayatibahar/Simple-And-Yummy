package com.hayatibahar.simpleandyummy.core.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
)