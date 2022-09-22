package com.cooking.cooklikeachef.presentation.screens.favourites_screen.events

import android.content.Context

sealed class FavouritesUIEvents {
    class SearchFavouriteRecipeChanged(val recipe: String) : FavouritesUIEvents()
    class ContactUs(val context: Context) : FavouritesUIEvents()
    object SignOff : FavouritesUIEvents()
    object DisplayOptionsMenu : FavouritesUIEvents()
    object DismissOptionsMenu : FavouritesUIEvents()
//    object DisplaySearchFavouriteRecipeDropdown : FavouritesUIEvents()
//    object DismissSearchFavouriteRecipeDropdown : FavouritesUIEvents()
}
