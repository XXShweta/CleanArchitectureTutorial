package com.android.cleanarchitectureproject.domain.model

data class Cocktail(
    val id: String,
    val name: String,
    val url: String,
    val tag: String?= null,
    val category: String?= null,
    val iba: String?= null,
    val alcoholicCategory : String? = null,
    val glassCategory: String?= null,
    val instructions: String? = null
)
