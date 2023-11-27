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
import com.android.cleanarchitectureproject.domain.model.Cocktail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val getCocktailsUseCase: GetCocktailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf<Resource<List<Cocktail>>>(Resource.Loading())
    val state: State<Resource<List<Cocktail>>> = _state

    init {
        getCocktails()
    }

    private fun getCocktails(){
        viewModelScope.launch {
            getCocktailsUseCase().collect{ result ->
                _state.value = result
            }
        }

    }

}
