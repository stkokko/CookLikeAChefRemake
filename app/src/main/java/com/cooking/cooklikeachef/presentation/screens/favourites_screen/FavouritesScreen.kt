package com.cooking.cooklikeachef.presentation.screens.favourites_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.navigation.Screens
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.common_compoments.OptionsMenu
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.components.FavouriteRecipesCard
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.events.FavouritesUIEvents
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.viewmodel.FavouritesViewModel
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.ui.theme.LightCherry

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
            when {
                boxWithConstraintsScope.maxHeight > 900.dp -> {
                    FavouritesContent(
                        optionsMenuIconSize = 38.dp,
                        optionsMenuDropdownWidth = 240.dp,
                        optionsMenuDropdownItemFontSize = 28.sp,
                        navController = navController,
                        favouritesViewModel = favouritesViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    FavouritesContent(
                        optionsMenuIconSize = 34.dp,
                        optionsMenuDropdownWidth = 200.dp,
                        optionsMenuDropdownItemFontSize = 22.sp,
                        navController = navController,
                        favouritesViewModel = favouritesViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 620.dp -> {
                    FavouritesContent(
                        optionsMenuIconSize = 26.dp,
                        optionsMenuDropdownWidth = 160.dp,
                        optionsMenuDropdownItemFontSize = 18.sp,
                        navController = navController,
                        favouritesViewModel = favouritesViewModel
                    )
                }

                else -> {
                    FavouritesContent(
                        navController = navController,
                        favouritesViewModel = favouritesViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun FavouritesContent(
    optionsMenuIconSize: Dp = 24.dp,
    optionsMenuDropdownWidth: Dp = 146.dp,
    optionsMenuDropdownItemFontSize: TextUnit = 16.sp,
    navController: NavController,
    favouritesViewModel: FavouritesViewModel
) {
    val state = favouritesViewModel.state

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OptionsMenu(
            backgroundColor = LightCherry,
            expandedOptionsMenu = state.value.displayOptionsMenu,
            iconSize = optionsMenuIconSize,
            dropdownMenuWidth = optionsMenuDropdownWidth,
            dropdownItemFontSize = optionsMenuDropdownItemFontSize,
            title = {
                // TODO
//                        OutlinedTextField(value = , onValueChange = )
            },
            openOptionsMenu = {
                favouritesViewModel.onEvent(FavouritesUIEvents.DisplayOptionsMenu)
            },
            closeOptionsMenu = {
                favouritesViewModel.onEvent(FavouritesUIEvents.OptionsMenuDismissed)
            }
        ) {
            favouritesViewModel.onEvent(FavouritesUIEvents.SignOff)
        }

        if (state.value.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.5f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        } else {

            if (state.value.favouriteRecipes.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.value.favouriteRecipes) { favouriteRecipe ->
                        FavouriteRecipesCard(
                            url = favouriteRecipe.imageURL,
                            title = favouriteRecipe.name
                        ) {
                            // TODO: navigate to RecipeScreen which is a
                            // TODO: fragment in the original implementation
                        }
                    }
                }
            } else {
                Text(
                    text = stringResource(id = R.string.list_is_empty),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    letterSpacing = 2.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }
        }
    }

    if (state.value.errorMessage.isNotEmpty()) {
        // TODO: Snackbar
    }

    if (!state.value.isLoggedIn) {
        LaunchedEffect(Unit) {
            navController.navigate(Screens.Login.name) {
                popUpTo(Screens.Favourites.name) {
                    inclusive = true
                }
            }
        }
    }
}