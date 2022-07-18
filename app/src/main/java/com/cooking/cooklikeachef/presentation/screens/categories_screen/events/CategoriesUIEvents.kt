package com.cooking.cooklikeachef.presentation.screens.categories_screen.events

sealed class CategoriesUIEvents {
    class SearchRecipeChanged(recipe: String) : CategoriesUIEvents()
    class SearchRecipeResults(recipe: String) : CategoriesUIEvents()
    object SignOff : CategoriesUIEvents()
    object DisplayOptionsMenu : CategoriesUIEvents()
    object DismissOptionsMenu : CategoriesUIEvents()
}
