package com.hayatibahar.simpleandyummy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayatibahar.simpleandyummy.core.common.ResponseState
import com.hayatibahar.simpleandyummy.core.common.mapTo
import com.hayatibahar.simpleandyummy.core.domain.model.Ingredient
import com.hayatibahar.simpleandyummy.core.domain.model.Recipe
import com.hayatibahar.simpleandyummy.core.domain.model.RecipeDetail
import com.hayatibahar.simpleandyummy.core.domain.usecase.DeleteGroceriesUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.GetRecipeDetailUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.InsertGroceriesUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.InsertRecipeUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.IsRecipeFavoriteUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.UpdateFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase,
    private val isRecipeFavoriteUseCase: IsRecipeFavoriteUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase,
    private val insertGroceriesUseCase: InsertGroceriesUseCase,
    private val deleteGroceriesUseCase: DeleteGroceriesUseCase,
    private val insertRecipeUseCase: InsertRecipeUseCase,
) :
    ViewModel() {

    private val _detailUiState = MutableLiveData<DetailUiState>()
    val detailUiState: LiveData<DetailUiState> get() = _detailUiState

    fun getRecipeDetail(id: String) {
        viewModelScope.launch {
            combine(
                getRecipeDetailUseCase(id),
                isRecipeFavoriteUseCase(id.toInt())
            ) { responseState, isFavorite ->
                when (responseState) {
                    is ResponseState.Loading -> {
                        _detailUiState.postValue(
                            DetailUiState.Loading
                        )
                    }

                    is ResponseState.Success -> {
                        _detailUiState.postValue(
                            DetailUiState.Success(
                                RecipeDetail(
                                    id = responseState.data.id,
                                    title = responseState.data.title,
                                    summary = responseState.data.summary,
                                    image = responseState.data.image,
                                    readyInMinutes = responseState.data.readyInMinutes,
                                    ingredients = responseState.data.ingredients,
                                    vegan = responseState.data.vegan,
                                    vegetarian = responseState.data.vegetarian,
                                    dairyFree = responseState.data.dairyFree,
                                    glutenFree = responseState.data.glutenFree,
                                    cheap = responseState.data.cheap,
                                    veryHealthy = responseState.data.veryHealthy,
                                    instructions = responseState.data.instructions,
                                    isFavorite = isFavorite,
                                    onFavorite = {
                                        updateFavorite(
                                            recipe = responseState.data.mapTo {
                                                Recipe(
                                                    it.id,
                                                    it.title,
                                                    it.image,
                                                    it.readyInMinutes
                                                )
                                            },
                                            isFavorite
                                        )
                                    }
                                )
                            )
                        )
                    }

                    is ResponseState.Error -> {
                        _detailUiState.postValue(
                            DetailUiState.Error(
                                responseState.message
                            )
                        )
                    }

                }
            }.collect()
        }
    }

    fun addToGroceries(ingredients: List<Ingredient>) {
        viewModelScope.launch {
            insertGroceriesUseCase.invoke(ingredients)
        }
    }

    fun deleteGroceries() {
        viewModelScope.launch {
            deleteGroceriesUseCase()
        }
    }

    private fun updateFavorite(recipe: Recipe, favorite: Boolean) {
        viewModelScope.launch {
            insertRecipeUseCase(recipe)
            if (favorite) {
                updateFavoriteStatusUseCase(recipe.id, false)
            } else {
                updateFavoriteStatusUseCase(recipe.id, true)
            }
        }
    }

}