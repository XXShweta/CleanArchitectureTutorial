package com.android.cleanarchitectureproject.di

import com.android.cleanarchitectureproject.common.Constants.BASE_URL
import com.android.cleanarchitectureproject.data.remote.api.TheCocktailDbApi
import com.android.cleanarchitectureproject.data.repository.CocktailRepositoryImpl
import com.android.cleanarchitectureproject.domain.repository.CocktailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun providesTheCocktailDbApi(retrofit: Retrofit): TheCocktailDbApi {
        return retrofit.create(TheCocktailDbApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCocktailRepository(api: TheCocktailDbApi): CocktailRepository =
        CocktailRepositoryImpl(api)

}
