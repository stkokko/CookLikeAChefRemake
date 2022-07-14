package com.cooking.cooklikeachef.presentation.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar
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
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
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
                        innerPadding = innerPadding,
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
                        innerPadding = innerPadding,
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 600.dp -> {
                    MainContent(
                        modifier = Modifier
                            .width(200.dp)
                            .height(120.dp)
                            .padding(end = 6.dp),
                        welcomeTextFontSize = 28.sp,
                        latestRecipesTextFontSize = 22.sp,
                        cardTextFontSize = 12.sp,
                        innerPadding = innerPadding,
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
                        innerPadding = innerPadding,
                        navController = navController,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}

// TODO
@Composable
fun OptionsMenu(
    expandedOptionsMenu: Boolean,
    openOptionsMenu: () -> Unit,
    closeOptionsMenu: () -> Unit,
    logOut: () -> Unit
) {
    TopAppBar(
        title = { },
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { openOptionsMenu() }) {
                Icon(Icons.Default.MoreVert, "")
            }

            DropdownMenu(
                expanded = expandedOptionsMenu,
                onDismissRequest = { closeOptionsMenu() }
            ) {

                DropdownMenuItem(onClick = {
                    // TODO: send email action
                }) {
                    Text(text = stringResource(id = R.string.contact_us))
                }

                DropdownMenuItem(onClick = {
                    logOut()
                }) {
                    Text(text = stringResource(id = R.string.logout))
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

@Composable
fun MainContent(
    modifier: Modifier,
    welcomeTextFontSize: TextUnit,
    latestRecipesTextFontSize: TextUnit,
    cardTextFontSize: TextUnit,
    innerPadding: PaddingValues,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    val state = mainViewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
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
                    top = 0.dp,
                    end = 0.dp,
                    bottom = innerPadding.calculateBottomPadding() + 6.dp
                )
        ) {
            OptionsMenu(
                expandedOptionsMenu = state.value.displayOptionsMenu,
                openOptionsMenu = {
                    mainViewModel.onEvent(MainUIEvents.DisplayOptionsMenu)
                },
                closeOptionsMenu = {
                    mainViewModel.onEvent(MainUIEvents.OptionsMenuDismissed)
                },
                logOut = {
                    mainViewModel.onEvent(MainUIEvents.SignOff)
                }
            )
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
        Spacer(modifier = Modifier.height(20.dp))
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