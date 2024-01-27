package com.hayatibahar.simpleandyummy.ui.detail

import com.hayatibahar.simpleandyummy.core.domain.model.RecipeDetail

sealed class DetailUiState {
    data object Loading : DetailUiState()
    data class Error(val errorMessage: String) : DetailUiState()
    data class Success(val data: RecipeDetail) : DetailUiState()
}