package com.cooking.cooklikeachef.presentation.screens.favourites_screen.viewmodel

import com.cooking.cooklikeachef.domain.model.Recipe

data class FavouritesState(
    val favouriteRecipes: List<Recipe> = emptyList(),
    val searchRecipe: String = "",
    val isLoading: Boolean = false,
    val errorMessageLogOut: String = "",
    val isLoggedIn: Boolean = true,
    val isOptionsMenuExpanded: Boolean = false,
    val isSearchRecipeDropdownExpanded: Boolean = false
)
