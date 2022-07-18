package com.cooking.cooklikeachef.presentation.screens.favourites_screen.events

sealed class FavouritesUIEvents {
    class SearchFavouriteRecipeChanged(recipe: String) : FavouritesUIEvents()
    class SearchFavouriteRecipeResults(recipe: String) : FavouritesUIEvents()
    object SignOff : FavouritesUIEvents()
    object DisplayOptionsMenu : FavouritesUIEvents()
    object DismissOptionsMenu : FavouritesUIEvents()
}
