package com.hayatibahar.simpleandyummy.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.core.domain.usecase.GetAllFavoriteRecipes
import com.hayatibahar.simpleandyummy.core.domain.usecase.UpdateFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoriteRecipes: GetAllFavoriteRecipes,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase,
) : ViewModel() {

    private val _favoriteRecipes = MutableLiveData<List<Recipe>>()
    val favoriteRecipes: LiveData<List<Recipe>> get() = _favoriteRecipes

    fun getFavoriteRecipes() {
        viewModelScope.launch {
            getAllFavoriteRecipes.invoke().collect {
                _favoriteRecipes.postValue(it)
            }
        }
    }

    fun updateFavoriteRecipe(recipeId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            updateFavoriteStatusUseCase.invoke(recipeId, isFavorite)
        }
    }
}