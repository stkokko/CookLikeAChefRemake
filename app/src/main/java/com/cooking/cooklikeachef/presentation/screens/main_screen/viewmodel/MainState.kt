package com.cooking.cooklikeachef.presentation.screens.main_screen.viewmodel

import com.cooking.cooklikeachef.domain.model.Recipe

data class MainState(
    val latestRecipesList: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isLoggedIn: Boolean = true,
    val displayOptionsMenu: Boolean = false
)
