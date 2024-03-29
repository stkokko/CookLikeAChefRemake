package com.cooking.cooklikeachef.presentation.screens.favourites_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomTopAppBar
import com.cooking.cooklikeachef.presentation.screens.common_compoments.OptionsMenu
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.viewmodel.FavouritesState
import com.cooking.cooklikeachef.presentation.ui.theme.LightCherry

@Composable
fun Content(
    scaffoldState: ScaffoldState,
    searchFavouriteFieldModifier: Modifier,
    searchFavouritePlaceholderFontSize: TextUnit = 16.sp,
    searchFavouriteTextStyleFontSize: TextUnit = 16.sp,
    optionsMenuIconSize: Dp = 24.dp,
    optionsMenuDropdownWidth: Dp = 146.dp,
    optionsMenuDropdownItemFontSize: TextUnit = 16.sp,
    navController: NavController,
    state: State<FavouritesState>,
    eventDisplayOptionsMenu: () -> Unit,
    eventDismissOptionsMenu: () -> Unit,
    eventContactUs: () -> Unit,
    eventSignOff: () -> Unit,
    eventSearchFavouriteRecipeChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CustomTopAppBar(backgroundColor = LightCherry, title = {
            OutlinedTextField(
                value = state.value.searchRecipe,
                onValueChange = { searchFavouriteRecipe ->
                    eventSearchFavouriteRecipeChanged(searchFavouriteRecipe.trim())
                },
                placeholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(id = R.string.search),
                            color = Color.LightGray,
                            fontSize = searchFavouritePlaceholderFontSize,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                },
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                shape = RoundedCornerShape(26.dp),
                textStyle = TextStyle(fontSize = searchFavouriteTextStyleFontSize),
                modifier = searchFavouriteFieldModifier
            )
        }) {
            OptionsMenu(
                isOptionsMenuExpanded = state.value.isOptionsMenuExpanded,
                iconSize = optionsMenuIconSize,
                dropdownMenuWidth = optionsMenuDropdownWidth,
                dropdownItemFontSize = optionsMenuDropdownItemFontSize,
                openOptionsMenu = { eventDisplayOptionsMenu() },
                dismissOptionsMenu = { eventDismissOptionsMenu() },
                contactUs = { eventContactUs() }) {
                eventSignOff()
            }
        }

        if (state.value.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
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
                            favouriteRecipe = favouriteRecipe
                        ) { favouriteRecipeId ->
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "id",
                                value = favouriteRecipeId
                            )
                            navController.navigate(Screens.Recipe.name)
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.list_is_empty),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        letterSpacing = 2.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

    if (state.value.errorMessageLogOut.isNotEmpty()) {
        LaunchedEffect(Unit) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = state.value.errorMessageLogOut,
                duration = SnackbarDuration.Short
            )
        }
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