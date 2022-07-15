package com.cooking.cooklikeachef.presentation.screens.favourites_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
    val state = favouritesViewModel.state

    Scaffold(bottomBar = {
        BottomNavigationBar(
            navController = navController
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            OptionsMenu(
                backgroundColor = LightCherry,
                expandedOptionsMenu = state.value.displayOptionsMenu,
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
                Log.d("FavouriteScreen", "favouriteRecipesList: ${state.value.favouriteRecipes}")
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