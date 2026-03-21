package com.example.andystudyone.model

data class Recipe(
    val id: Int,
    val name: String,
    val instructions: List<String>,
    val image: String,
)

data class RecipeResponse(val recipes: List<Recipe>)


