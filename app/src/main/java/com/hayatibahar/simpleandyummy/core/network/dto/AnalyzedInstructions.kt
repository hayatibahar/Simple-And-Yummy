package com.hayatibahar.simpleandyummy.core.network.dto

data class AnalyzedInstructions(
    val name: String,
    val steps: List<Step>,
)