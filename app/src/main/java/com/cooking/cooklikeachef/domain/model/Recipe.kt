package com.cooking.cooklikeachef.domain.model

data class Recipe(
    val name: String = "",
    val category: String = "",
    val imageURL: String = "",
    val ingredients: List<Ingredient> = emptyList<Ingredient>(),
    val steps: String = "",
    val comment: List<Comment> = emptyList(),
    val language: String = "EN",
    val timestamp: String = "-1"
)
