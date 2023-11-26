package com.android.cleanarchitectureproject.presentation.cocktail_list

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import com.android.cleanarchitectureproject.domain.use_case.get_cocktail.GetCocktailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.android.cleanarchitectureproject.common.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val getCocktailsUseCase: GetCocktailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf<CocktailListState>(CocktailListState())
    val state: State<CocktailListState> = _state

    init {
        getCocktails()
    }

    private fun getCocktails(){
        getCocktailsUseCase().onEach { result ->
            when(result){
                is Resource.Error -> {
                    _state.value = CocktailListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CocktailListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CocktailListState(cocktails = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

}
