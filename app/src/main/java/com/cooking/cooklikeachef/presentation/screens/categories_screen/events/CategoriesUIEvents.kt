package com.cooking.cooklikeachef.presentation.screens.categories_screen.events

import com.cooking.cooklikeachef.presentation.screens.main_screen.events.MainUIEvents

sealed class CategoriesUIEvents {
    class SearchRecipeChanged(recipe: String) : CategoriesUIEvents()
    class SearchRecipeResults(recipe: String) : CategoriesUIEvents()
    object SignOff : CategoriesUIEvents()
    object DisplayOptionsMenu : CategoriesUIEvents()
    object OptionsMenuDismissed : CategoriesUIEvents()
}
