package com.cooking.cooklikeachef.presentation.screens.home_screen.viewmodel

import com.cooking.cooklikeachef.domain.model.Recipe

data class HomeState(
    val latestRecipesList: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessageLogOut: String = "",
    val isLoggedIn: Boolean = true,
    val isOptionsMenuExpanded: Boolean = false,
    val isExitAppDialogOpen: Boolean = false
)
