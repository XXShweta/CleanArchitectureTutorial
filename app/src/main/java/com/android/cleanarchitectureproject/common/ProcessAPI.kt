package com.android.cleanarchitectureproject.common

import androidx.compose.runtime.Composable
import com.android.cleanarchitectureproject.common.Constants.UNEXPECTED_ERROR
import com.android.cleanarchitectureproject.common.component.ErrorComposable
import com.android.cleanarchitectureproject.common.component.LoadingComposable

@Composable
fun<T> ProcessAPI(
    state: Resource<T>,
    content: @Composable () -> Unit
){
    when(state){
        is Resource.Error -> {
            ErrorComposable(state.message ?: UNEXPECTED_ERROR)
        }
        is Resource.Loading -> {
            LoadingComposable()
        }
        is Resource.Success -> {
            content()
        }
    }

}
