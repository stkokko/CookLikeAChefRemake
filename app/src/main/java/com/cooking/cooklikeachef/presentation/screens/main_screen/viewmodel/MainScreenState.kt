package com.cooking.cooklikeachef.presentation.screens.main_screen.viewmodel

import com.cooking.cooklikeachef.domain.model.Recipe

data class MainScreenState(
    val recipesList: List<Recipe> = emptyList(),
    val latestRecipesList: List<Recipe> = emptyList(),
    val favouriteRecipesList: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val message: String = "",
    val isUserLoggedIn: Boolean = false
)
