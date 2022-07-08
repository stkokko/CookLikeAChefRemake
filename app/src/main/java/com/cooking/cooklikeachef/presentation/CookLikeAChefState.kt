package com.cooking.cooklikeachef.presentation

import com.cooking.cooklikeachef.domain.model.Recipe

data class CookLikeAChefState(
    val recipesList: List<Recipe> = emptyList(),
    val latestRecipesList: List<Recipe> = emptyList(),
    val favouriteRecipesList: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val message: String = "",
    val isUserLoggedIn: Boolean = false
)
