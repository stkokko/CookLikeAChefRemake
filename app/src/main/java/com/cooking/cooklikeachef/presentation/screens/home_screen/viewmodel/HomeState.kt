package com.cooking.cooklikeachef.presentation.screens.home_screen.viewmodel

import com.cooking.cooklikeachef.domain.model.Recipe

data class HomeState(
    val latestRecipesList: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isLoggedIn: Boolean = true,
    val expandedOptionsMenu: Boolean = false
)
