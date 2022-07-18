package com.cooking.cooklikeachef.presentation.screens.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.main_screen.components.Content
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
                    Content(
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
                        state = mainViewModel.state,
                        eventDisplayOptionsMenu = {
                            mainViewModel.onEvent(MainUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            mainViewModel.onEvent(MainUIEvents.DismissOptionsMenu)
                        },
                        eventSignOff = {
                            mainViewModel.onEvent(MainUIEvents.SignOff)
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    Content(
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
                        state = mainViewModel.state,
                        eventDisplayOptionsMenu = {
                            mainViewModel.onEvent(MainUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            mainViewModel.onEvent(MainUIEvents.DismissOptionsMenu)
                        },
                        eventSignOff = {
                            mainViewModel.onEvent(MainUIEvents.SignOff)
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 620.dp -> {
                    Content(
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
                        state = mainViewModel.state,
                        eventDisplayOptionsMenu = {
                            mainViewModel.onEvent(MainUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            mainViewModel.onEvent(MainUIEvents.DismissOptionsMenu)
                        },
                        eventSignOff = {
                            mainViewModel.onEvent(MainUIEvents.SignOff)
                        }
                    )
                }
                else -> {
                    Content(
                        modifier = Modifier
                            .width(180.dp)
                            .height(100.dp)
                            .padding(end = 6.dp),
                        welcomeTextFontSize = 24.sp,
                        latestRecipesTextFontSize = 18.sp,
                        cardTextFontSize = 10.sp,
                        navController = navController,
                        state = mainViewModel.state,
                        eventDisplayOptionsMenu = {
                            mainViewModel.onEvent(MainUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            mainViewModel.onEvent(MainUIEvents.DismissOptionsMenu)
                        },
                        eventSignOff = {
                            mainViewModel.onEvent(MainUIEvents.SignOff)
                        }
                    )
                }
            }
        }
    }
}