package com.android.cleanarchitectureproject.domain.repository

import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.domain.model.Cocktail
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {

     suspend fun getCocktails(): Flow<Resource<List<Cocktail>>>

     suspend fun getCocktailById(id: String): Flow<Resource<Cocktail>>

}
