package com.cooking.cooklikeachef.presentation.screens.categories_screen.events

import android.content.Context

sealed class CategoriesUIEvents {
    class SearchRecipeChanged(val recipe: String) : CategoriesUIEvents()
    class ContactUs(val context: Context) : CategoriesUIEvents()
    object SignOff : CategoriesUIEvents()
    object DisplayOptionsMenu : CategoriesUIEvents()
    object DismissOptionsMenu : CategoriesUIEvents()
    object DismissSearchRecipeDropdown : CategoriesUIEvents()
}
