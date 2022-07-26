package com.cooking.cooklikeachef.presentation.screens.recipe_screen.view_model

import com.cooking.cooklikeachef.domain.model.Recipe

data class RecipeState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)
