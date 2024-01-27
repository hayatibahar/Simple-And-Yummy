package com.hayatibahar.simpleandyummy.ui.grocery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hayatibahar.simpleandyummy.core.database.entity.GroceryEntity
import com.hayatibahar.simpleandyummy.core.domain.usecase.DeleteCheckedGroceriesUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.DeleteGroceriesUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.GetGroceriesUseCase
import com.hayatibahar.simpleandyummy.core.domain.usecase.UpdateGroceryCheckStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryViewModel @Inject constructor(
    private val getGroceriesUseCase: GetGroceriesUseCase,
    private val updateGroceryCheckStatusUseCase: UpdateGroceryCheckStatusUseCase,
    private val deleteCheckedGroceriesUseCase: DeleteCheckedGroceriesUseCase,
    private val deleteGroceriesUseCase: DeleteGroceriesUseCase,
) : ViewModel() {

    private val _groceries = MutableLiveData<List<GroceryEntity>>()

    val groceries: LiveData<List<GroceryEntity>> get() = _groceries

    fun getGroceries() {
        viewModelScope.launch {
            getGroceriesUseCase.invoke().collect {
                _groceries.postValue(it)
            }
        }
    }

    fun updateGroceryCheckStatusUseCase(id: Int, isChecked: Boolean) {
        viewModelScope.launch {
            updateGroceryCheckStatusUseCase.invoke(id, isChecked)
        }
    }

    fun deleteCheckedGroceries() {
        viewModelScope.launch {
            deleteCheckedGroceriesUseCase()
        }
    }

    fun deleteGroceries() {
        viewModelScope.launch {
            deleteGroceriesUseCase()
        }
    }

}