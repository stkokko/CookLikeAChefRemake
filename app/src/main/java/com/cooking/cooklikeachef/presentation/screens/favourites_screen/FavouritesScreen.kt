package com.cooking.cooklikeachef.presentation.screens.favourites_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
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
    Scaffold(bottomBar = {
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
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    Content(
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
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 620.dp -> {
                    Content(
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
                        }
                    )
                }
                else -> {
                    Content(
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
                        }
                    )
                }
            }
        }
    }
}