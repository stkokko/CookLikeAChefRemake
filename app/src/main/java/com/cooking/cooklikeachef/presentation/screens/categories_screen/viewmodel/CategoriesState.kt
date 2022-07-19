package com.cooking.cooklikeachef.presentation.screens.categories_screen.viewmodel

import com.cooking.cooklikeachef.domain.model.Recipe

data class CategoriesState(
    val recipes: List<Recipe> = emptyList(),
    val searchRecipe: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isLoggedIn: Boolean = true,
    val expandedOptionsMenu: Boolean = false,
    val expandedSearchRecipeDropdown: Boolean = false
)
