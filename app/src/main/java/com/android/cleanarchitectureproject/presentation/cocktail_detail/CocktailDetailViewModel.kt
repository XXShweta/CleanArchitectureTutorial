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
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.domain.use_case.get_cocktails.GetCocktailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val getCocktailUseCase: GetCocktailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf<Resource<Cocktail>>(Resource.Loading())
    val state: State<Resource<Cocktail>> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COCKTAIL_ID)?.let { id ->
            getCocktail(id)
        }
    }

    private fun getCocktail(id: String){
        viewModelScope.launch {
            getCocktailUseCase(id).collect{ result ->
                _state.value = result
            }
        }
    }


}
