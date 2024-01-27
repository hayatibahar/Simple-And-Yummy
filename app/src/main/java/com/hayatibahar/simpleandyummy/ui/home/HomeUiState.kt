package com.hayatibahar.simpleandyummy.ui.home

import com.hayatibahar.simpleandyummy.core.domain.model.Recipe

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Error(val errorMessage: String) : HomeUiState()
    data class Success(val data: HomeData) : HomeUiState()
}

data class HomeData(
    val recipes: List<Recipe> = emptyList(),
    var shouldScrollUp: Boolean = false,
    var noMatch: Boolean = false,
)