package com.cooking.cooklikeachef.presentation.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
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
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomTopAppBar
import com.cooking.cooklikeachef.presentation.screens.common_compoments.ExitAppDialog
import com.cooking.cooklikeachef.presentation.screens.common_compoments.OptionsMenu
import com.cooking.cooklikeachef.presentation.screens.home_screen.viewmodel.HomeState

@Composable
fun Content(
    scaffoldState: ScaffoldState,
    latestRecipesCardModifier: Modifier,
    welcomeTextFontSize: TextUnit,
    latestRecipesTextFontSize: TextUnit,
    cardTextFontSize: TextUnit,
    optionsMenuIconSize: Dp = 24.dp,
    optionsMenuDropdownWidth: Dp = 146.dp,
    optionsMenuDropdownItemFontSize: TextUnit = 16.sp,
    navController: NavController,
    state: State<HomeState>,
    eventDisplayOptionsMenu: () -> Unit,
    eventDismissOptionsMenu: () -> Unit,
    eventContactUs: () -> Unit,
    eventSignOff: () -> Unit,
    eventExitApp: () -> Unit,
    eventDismissExitAppDialog: () -> Unit
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
            CustomTopAppBar(modifier = Modifier.align(Alignment.TopCenter)) {
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
                                modifier = latestRecipesCardModifier,
                                latestRecipe = latestRecipe,
                                textFontSize = cardTextFontSize
                            ) { recipeId ->
                                // TODO: navigate passing id of object
                                navController.navigate(Screens.Recipe.name)
                            }
                        }
                    }
                } else {
                    LazyRow {
                        items (count = 4) {
                            LatestRecipesCard(
                                modifier = latestRecipesCardModifier,
                                isError = true
                            )
                        }
                    }
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

    if (state.value.isExitAppDialogOpen) {
        ExitAppDialog(onExitClick = {
            eventExitApp()
        }) {
            eventDismissExitAppDialog()
        }
    }

    if (!state.value.isLoggedIn) {
        LaunchedEffect(Unit) {
            navController.navigate(Screens.Login.name) {
                popUpTo(Screens.Home.name) {
                    inclusive = true
                }
            }
        }
    }
}