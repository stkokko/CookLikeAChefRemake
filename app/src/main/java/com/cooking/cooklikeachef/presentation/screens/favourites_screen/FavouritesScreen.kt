package com.cooking.cooklikeachef.presentation.screens.favourites_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.events.FavouritesUIEvents
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.viewmodel.FavouritesViewModel
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.components.Content

@Composable
fun FavouritesScreen(
    navController: NavController,
    favouritesViewModel: FavouritesViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val boxWithConstraintsScope = this
            val context = LocalContext.current
            when {
                boxWithConstraintsScope.maxHeight > 900.dp -> {
                    Content(
                        scaffoldState = scaffoldState,
                        searchFavouriteFieldModifier = Modifier
                            .width(380.dp)
                            .height(60.dp),
                        searchFavouritePlaceholderFontSize = 22.sp,
                        searchFavouriteTextStyleFontSize = 22.sp,
                        optionsMenuIconSize = 38.dp,
                        optionsMenuDropdownWidth = 240.dp,
                        optionsMenuDropdownItemFontSize = 28.sp,
                        navController = navController,
                        state = favouritesViewModel.state,
                        eventDisplayOptionsMenu = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.SignOff)
                        },
                        eventSearchFavouriteRecipeChanged = { favouriteRecipe ->
                            favouritesViewModel.onEvent(
                                FavouritesUIEvents.SearchFavouriteRecipeChanged(
                                    favouriteRecipe
                                )
                            )
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    Content(
                        scaffoldState = scaffoldState,
                        searchFavouriteFieldModifier = Modifier
                            .width(350.dp)
                            .height(58.dp),
                        searchFavouritePlaceholderFontSize = 22.sp,
                        searchFavouriteTextStyleFontSize = 22.sp,
                        optionsMenuIconSize = 34.dp,
                        optionsMenuDropdownWidth = 200.dp,
                        optionsMenuDropdownItemFontSize = 22.sp,
                        navController = navController,
                        state = favouritesViewModel.state,
                        eventDisplayOptionsMenu = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.SignOff)
                        },
                        eventSearchFavouriteRecipeChanged = { favouriteRecipe ->
                            favouritesViewModel.onEvent(
                                FavouritesUIEvents.SearchFavouriteRecipeChanged(
                                    favouriteRecipe
                                )
                            )
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 620.dp -> {
                    Content(
                        scaffoldState = scaffoldState,
                        searchFavouriteFieldModifier = Modifier
                            .width(270.dp)
                            .height(56.dp),
                        searchFavouritePlaceholderFontSize = 20.sp,
                        searchFavouriteTextStyleFontSize = 20.sp,
                        optionsMenuIconSize = 26.dp,
                        optionsMenuDropdownWidth = 170.dp,
                        optionsMenuDropdownItemFontSize = 18.sp,
                        navController = navController,
                        state = favouritesViewModel.state,
                        eventDisplayOptionsMenu = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.SignOff)
                        },
                        eventSearchFavouriteRecipeChanged = { favouriteRecipe ->
                            favouritesViewModel.onEvent(
                                FavouritesUIEvents.SearchFavouriteRecipeChanged(
                                    favouriteRecipe
                                )
                            )
                        }
                    )
                }
                else -> {
                    Content(
                        scaffoldState = scaffoldState,
                        searchFavouriteFieldModifier = Modifier
                            .width(240.dp)
                            .height(52.dp),
                        navController = navController,
                        state = favouritesViewModel.state,
                        eventDisplayOptionsMenu = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            favouritesViewModel.onEvent(FavouritesUIEvents.SignOff)
                        },
                        eventSearchFavouriteRecipeChanged = { favouriteRecipe ->
                            favouritesViewModel.onEvent(
                                FavouritesUIEvents.SearchFavouriteRecipeChanged(
                                    favouriteRecipe
                                )
                            )
                        }
                    )
                }
            }
        }
    }

    BackHandler {

    }
}