package com.cooking.cooklikeachef.presentation.screens.categories_screen.viewmodel

import com.cooking.cooklikeachef.domain.model.Recipe

data class CategoriesState(
    val recipes: List<Recipe> = emptyList(),
    val searchRecipe: String = "",
    val isLoading: Boolean = false,
    val errorMessageLogOut: String = "",
    val errorMessageRecipes: String = "",
    val isLoggedIn: Boolean = true,
    val isOptionsMenuExpanded: Boolean = false,
    val isSearchRecipeDropdownExpanded: Boolean = false
)
