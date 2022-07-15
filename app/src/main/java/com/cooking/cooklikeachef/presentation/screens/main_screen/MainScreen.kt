package com.cooking.cooklikeachef.presentation.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.common_compoments.OptionsMenu
import com.cooking.cooklikeachef.presentation.screens.main_screen.components.LatestRecipesCard
import com.cooking.cooklikeachef.presentation.screens.main_screen.events.MainUIEvents
import com.cooking.cooklikeachef.presentation.screens.main_screen.viewmodel.MainViewModel

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
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
                    MainContent(
                        modifier = Modifier
                            .width(370.dp)
                            .height(220.dp)
                            .padding(end = 6.dp),
                        welcomeTextFontSize = 44.sp,
                        latestRecipesTextFontSize = 38.sp,
                        cardTextFontSize = 18.sp,
                        optionsMenuIconSize = 38.dp,
                        optionsMenuDropdownWidth = 240.dp,
                        optionsMenuDropdownItemFontSize = 28.sp,
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    MainContent(
                        modifier = Modifier
                            .width(280.dp)
                            .height(150.dp)
                            .padding(end = 6.dp),
                        welcomeTextFontSize = 40.sp,
                        latestRecipesTextFontSize = 32.sp,
                        cardTextFontSize = 14.sp,
                        optionsMenuIconSize = 34.dp,
                        optionsMenuDropdownWidth = 200.dp,
                        optionsMenuDropdownItemFontSize = 22.sp,
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 620.dp -> {
                    MainContent(
                        modifier = Modifier
                            .width(200.dp)
                            .height(120.dp)
                            .padding(end = 6.dp),
                        welcomeTextFontSize = 28.sp,
                        latestRecipesTextFontSize = 22.sp,
                        cardTextFontSize = 12.sp,
                        optionsMenuIconSize = 26.dp,
                        optionsMenuDropdownWidth = 160.dp,
                        optionsMenuDropdownItemFontSize = 18.sp,
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }

                else -> {
                    MainContent(
                        modifier = Modifier
                            .width(180.dp)
                            .height(100.dp)
                            .padding(end = 6.dp),
                        welcomeTextFontSize = 24.sp,
                        latestRecipesTextFontSize = 18.sp,
                        cardTextFontSize = 10.sp,
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}

// TODO should we move all composable like main content to components package?

@Composable
fun MainContent(
    modifier: Modifier,
    welcomeTextFontSize: TextUnit,
    latestRecipesTextFontSize: TextUnit,
    cardTextFontSize: TextUnit,
    optionsMenuIconSize: Dp = 24.dp,
    optionsMenuDropdownWidth: Dp = 146.dp,
    optionsMenuDropdownItemFontSize: TextUnit = 16.sp,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val state = mainViewModel.state

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
                expandedOptionsMenu = state.value.displayOptionsMenu,
                iconSize = optionsMenuIconSize,
                dropdownMenuWidth = optionsMenuDropdownWidth,
                dropdownItemFontSize = optionsMenuDropdownItemFontSize,
                openOptionsMenu = {
                    mainViewModel.onEvent(MainUIEvents.DisplayOptionsMenu)
                },
                closeOptionsMenu = {
                    mainViewModel.onEvent(MainUIEvents.OptionsMenuDismissed)
                }
            ) {
                mainViewModel.onEvent(MainUIEvents.SignOff)
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