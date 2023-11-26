package com.android.cleanarchitectureproject.presentation.cocktail_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cleanarchitectureproject.common.Constants
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.domain.use_case.get_cocktails.GetCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val getCocktailUseCase: GetCocktailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(CocktailDetailState())
    val state: State<CocktailDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COCKTAIL_ID)?.let { id ->
            getCocktail(id)
        }
    }

    private fun getCocktail(id: String){
        getCocktailUseCase(id).onEach {
            when(it){
                is Resource.Error -> {
                    _state.value = CocktailDetailState(
                        error = it.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CocktailDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CocktailDetailState(cocktail = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }


}
