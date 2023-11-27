package com.android.cleanarchitectureproject.data.repository

import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.data.remote.api.TheCocktailDbApi
import com.android.cleanarchitectureproject.data.remote.dto.toCocktail
import com.android.cleanarchitectureproject.data.util.handleApiCall
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.domain.repository.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val api: TheCocktailDbApi
) : CocktailRepository{

    override suspend fun getCocktails(): Flow<Resource<List<Cocktail>>> {
        return flow{
            emit(Resource.Loading())
            val response = handleApiCall(apiCall = { api.getCocktails() }) { cocktailsDto ->
                cocktailsDto.drinks.map { it.toCocktail() }
            }
           emit(response)
        }
    }

    override suspend fun getCocktailById(id: String): Flow<Resource<Cocktail>> {
        return flow {
            emit(Resource.Loading())
            val response =  handleApiCall( apiCall = {api.getCocktailById(id)}){
                it.drinks[0].toCocktail()
            }
            emit(response)
        }
    }

}
