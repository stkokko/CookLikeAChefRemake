package com.cooking.cooklikeachef.presentation.screens.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens
import com.cooking.cooklikeachef.presentation.screens.common_compoments.OptionsMenu
import com.cooking.cooklikeachef.presentation.screens.main_screen.viewmodel.MainState

@Composable
fun Content(
    modifier: Modifier,
    welcomeTextFontSize: TextUnit,
    latestRecipesTextFontSize: TextUnit,
    cardTextFontSize: TextUnit,
    optionsMenuIconSize: Dp = 24.dp,
    optionsMenuDropdownWidth: Dp = 146.dp,
    optionsMenuDropdownItemFontSize: TextUnit = 16.sp,
    navController: NavController,
    state: State<MainState>,
    eventDisplayOptionsMenu: () -> Unit,
    eventDismissOptionsMenu: () -> Unit,
    eventSignOff: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_image_background),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 6.dp,
                    bottom = 6.dp
                )
        ) {
            OptionsMenu(
                title = {},
                expandedOptionsMenu = state.value.displayOptionsMenu,
                iconSize = optionsMenuIconSize,
                dropdownMenuWidth = optionsMenuDropdownWidth,
                dropdownItemFontSize = optionsMenuDropdownItemFontSize,
                openOptionsMenu = {
                    eventDisplayOptionsMenu()
                },
                closeOptionsMenu = {
                    eventDismissOptionsMenu()
                }
            ) {
                eventSignOff()
            }
            Text(
                text = stringResource(id = R.string.welcome_to_appname),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = welcomeTextFontSize,
                modifier = Modifier.align(alignment = Alignment.CenterStart)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter)
            ) {
                Text(
                    text = stringResource(id = R.string.latest_recipes),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = latestRecipesTextFontSize
                )

                if (state.value.latestRecipesList.isNotEmpty()) {
                    LazyRow {
                        items(state.value.latestRecipesList) { latestRecipe ->
                            LatestRecipesCard(
                                modifier = modifier,
                                url = latestRecipe.imageURL,
                                title = latestRecipe.name,
                                textFontSize = cardTextFontSize
                            ) {
                                // TODO: navigate
                            }
                        }
                    }
                }
            }
        }
    }

    if (state.value.errorMessage.isNotEmpty()) {
        // TODO: Snackbar
    }

    if (!state.value.isLoggedIn) {
        LaunchedEffect(Unit) {
            navController.navigate(Screens.Login.name) {
                popUpTo(Screens.Main.name) {
                    inclusive = true
                }
            }
        }
    }
}