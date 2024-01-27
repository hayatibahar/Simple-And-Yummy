package com.hayatibahar.simpleandyummy.ui.home

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayatibahar.simpleandyummy.core.common.ResponseState
import com.hayatibahar.simpleandyummy.core.domain.usecase.GetAllRecipesUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.GetDarkModeStateUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.GetRecipesWithSearchUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.SaveDarkModeStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val getRecipesWithSearchUseCase: GetRecipesWithSearchUseCase,
    private val getDarkModeStateUseCase: GetDarkModeStateUseCase,
    private val saveDarkModeStateUseCase: SaveDarkModeStateUseCase,
) :
    ViewModel() {

    private val _homeUiState = MutableLiveData<HomeUiState>()
    val homeUiState: LiveData<HomeUiState> get() = _homeUiState

    private val _isDarkMode = MutableLiveData(false)
    val isDarkMode: LiveData<Boolean> get() = _isDarkMode

    init {
        getAllRecipes()
        isAppDarkMode()
    }

    fun getAllRecipes() {
        viewModelScope.launch {
            getAllRecipesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> {
                        _homeUiState.postValue(
                            HomeUiState.Loading
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.postValue(
                            HomeUiState.Success(
                                HomeData(
                                    recipes = responseState.data.shuffled(),
                                    shouldScrollUp = false,
                                    noMatch = responseState.data.isEmpty()
                                )
                            )
                        )
                    }

                    is ResponseState.Error -> {
                        _homeUiState.postValue(
                            HomeUiState.Error(
                                errorMessage = responseState.message
                            )
                        )
                    }

                }
            }
        }
    }

    fun getRecipesWithSearch(
        query: String? = null,
        cuisine: String? = null,
        type: String? = null,
        diet: String? = null,
    ) {
        viewModelScope.launch {
            getRecipesWithSearchUseCase(
                query.orEmpty(),
                cuisine.orEmpty(),
                type.orEmpty(),
                diet.orEmpty()
            ).collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> {
                        _homeUiState.postValue(
                            HomeUiState.Loading
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.postValue(
                            HomeUiState.Success(
                                HomeData(
                                    recipes = responseState.data.shuffled(),
                                    shouldScrollUp = true,
                                    noMatch = responseState.data.isEmpty()
                                )
                            )
                        )
                    }

                    is ResponseState.Error -> {
                        _homeUiState.postValue(
                            HomeUiState.Error(
                                errorMessage = responseState.message
                            )
                        )
                    }

                }
            }
        }
    }

    private fun isAppDarkMode() {
        viewModelScope.launch {
            getDarkModeStateUseCase().collect {
                _isDarkMode.postValue(it)
                if (it) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
    }

    fun saveDarkModeState(isDarkMode : Boolean){
        viewModelScope.launch {
            saveDarkModeStateUseCase(isDarkMode)
        }
    }
}