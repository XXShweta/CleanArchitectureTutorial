package com.android.cleanarchitectureproject.domain.use_case.get_cocktail

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.android.cleanarchitectureproject.common.Resource
import com.android.cleanarchitectureproject.data.remote.dto.toCocktail
import com.android.cleanarchitectureproject.domain.model.Cocktail
import com.android.cleanarchitectureproject.domain.repository.CocktailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCocktailsUseCase @Inject constructor(
    private val repository: CocktailRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(): Flow<Resource<List<Cocktail>>> = flow{
        try {
            emit(Resource.Loading())
            val cocktails = repository.getCocktails().drinks.map { it.toCocktail() }
            emit(Resource.Success(
                cocktails
            ))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?: "Something went wrong"))
        }catch (e: IOException){
            emit(Resource.Error("Check your Internet Connection!"))
        }
    }
}
