package com.cooking.cooklikeachef.domain.model

data class Recipe(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val imageURL: String = "",
    val ingredients: List<Ingredient> = emptyList(),
    val steps: String = "",
    val comments: List<Comment> = emptyList(),
    val language: String = "EN",
    val timestamp: String = "-1"
)
