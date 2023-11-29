package com.android.cleanarchitectureproject.presentation.cocktail_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cleanarchitectureproject.common.Constants
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.di.IoDispatcher
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.domain.use_case.get_cocktail_by_id.GetCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val getCocktailUseCase: GetCocktailUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = MutableStateFlow<Resource<Cocktail>>(Resource.Loading())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_COCKTAIL_ID)?.let { id ->
            getCocktail(id)
        }
    }

    private fun getCocktail(id: String){
        viewModelScope.launch(dispatcher) {
            getCocktailUseCase(id).collect{ result ->
                _state.emit(result)
            }
        }
    }


}
