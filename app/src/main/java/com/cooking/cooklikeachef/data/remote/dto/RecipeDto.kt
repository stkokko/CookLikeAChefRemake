package com.cooking.cooklikeachef.data.remote.dto

import com.cooking.cooklikeachef.domain.model.Comment

data class RecipeDto(
    val name: String = "",
    val category: String = "",
    val imageURL: String = "",
    val ingredients: Map<String, List<String>> = emptyMap(),
    val steps: String = "",
    val comment: List<Comment> = emptyList(),
    val language: String = "EN",
    val timestamp: String = "-1"
)
