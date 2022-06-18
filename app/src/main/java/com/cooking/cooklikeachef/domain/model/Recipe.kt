package com.cooking.cooklikeachef.domain.model

data class Recipe(
    val name: String,
    val category: String,
    val imageURL: String,
    val ingredients: List<Ingredient>,
    val steps: String,
    val comment: List<Comment>,
    val language: String
)