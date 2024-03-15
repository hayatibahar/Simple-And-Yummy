package com.hayatibahar.simpleandyummy.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.hayatibahar.simpleandyummy.core.common.showToast
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Error(val errorMessage: String) : HomeUiState()
    data class Success(val data: HomeData) : HomeUiState()
}

data class HomeData(
    val recipes: List<Recipe> = emptyList(),
    private var shouldScrollUp: Boolean = false,
    private var noMatch: Boolean = false,
) {
    fun scrollIfNeeded(recyclerView: RecyclerView) {
        if (shouldScrollUp) {
            recyclerView.scrollToPosition(0)
            shouldScrollUp = false
        }
    }

    fun showToastIfNeeded(context: Context) {
        if (noMatch) {
            showToast(context, "No matching result found")
            noMatch = false
        }
    }
}