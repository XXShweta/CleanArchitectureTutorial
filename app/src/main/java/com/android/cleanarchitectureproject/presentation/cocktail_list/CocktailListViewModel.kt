package com.android.cleanarchitectureproject.presentation.cocktail_list

import androidx.lifecycle.ViewModel
import com.android.cleanarchitectureproject.domain.use_case.get_cocktail.GetCocktailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.di.IoDispatcher
import com.android.cleanarchitectureproject.domain.model.Cocktail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    private val getCocktailsUseCase: GetCocktailsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<List<Cocktail>>>(Resource.Loading())
    val state = _state.asStateFlow()

    init {
        getCocktails()
    }

    private fun getCocktails(){
        viewModelScope.launch(dispatcher) {
            getCocktailsUseCase().collect{ result ->
                _state.emit(result)
            }
        }

    }

}
