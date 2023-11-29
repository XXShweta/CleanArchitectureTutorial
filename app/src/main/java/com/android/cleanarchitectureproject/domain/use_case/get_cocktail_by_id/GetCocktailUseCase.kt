package com.android.cleanarchitectureproject.domain.use_case.get_cocktail_by_id

import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.domain.repository.CocktailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCocktailUseCase @Inject constructor(
    private val repository: CocktailRepository
) {
    suspend operator fun invoke(id: String): Flow<Resource<Cocktail>> {
        return repository.getCocktailById(id)
    }

}
