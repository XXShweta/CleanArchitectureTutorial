package com.android.cleanarchitectureproject.domain.use_case.get_cocktail

import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.domain.repository.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCocktailsUseCase @Inject constructor(
    private val repository: CocktailRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Cocktail>>> {
        return flow {
            repository.getCocktails().collect{ result ->
                when(result){
                    is Resource.Success -> {
                        val list = result.data?.sortedBy { it.name }?.filter { it.name.length > 2 }
                        emit(Resource.Success(list?: emptyList()))
                    }
                    else -> {
                        emit(result)
                    }
                }
            }
        }
    }

}
