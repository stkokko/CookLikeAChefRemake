package com.cooking.cooklikeachef.presentation.screens.recipe_screen.events

import com.cooking.cooklikeachef.domain.model.Recipe

sealed class RecipeUIEvents {
    class UpdateFavourites(val recipe: Recipe) : RecipeUIEvents()
}
