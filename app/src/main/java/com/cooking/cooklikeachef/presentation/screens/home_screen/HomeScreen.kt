package com.cooking.cooklikeachef.presentation.screens.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.home_screen.components.Content
import com.cooking.cooklikeachef.presentation.screens.home_screen.events.HomeUIEvents
import com.cooking.cooklikeachef.presentation.screens.home_screen.viewmodel.HomeViewModel

@Composable
fun MainScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
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
        ) { // TODO: exit app dialog
            val boxWithConstraintsScope = this
            val context = LocalContext.current
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
                        state = homeViewModel.state,
                        eventDisplayOptionsMenu = {
                            homeViewModel.onEvent(HomeUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            homeViewModel.onEvent(HomeUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            homeViewModel.onEvent(HomeUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            homeViewModel.onEvent(HomeUIEvents.SignOff)
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
                        state = homeViewModel.state,
                        eventDisplayOptionsMenu = {
                            homeViewModel.onEvent(HomeUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            homeViewModel.onEvent(HomeUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            homeViewModel.onEvent(HomeUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            homeViewModel.onEvent(HomeUIEvents.SignOff)
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
                        optionsMenuDropdownWidth = 170.dp,
                        optionsMenuDropdownItemFontSize = 18.sp,
                        navController = navController,
                        state = homeViewModel.state,
                        eventDisplayOptionsMenu = {
                            homeViewModel.onEvent(HomeUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            homeViewModel.onEvent(HomeUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            homeViewModel.onEvent(HomeUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            homeViewModel.onEvent(HomeUIEvents.SignOff)
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
                        state = homeViewModel.state,
                        eventDisplayOptionsMenu = {
                            homeViewModel.onEvent(HomeUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            homeViewModel.onEvent(HomeUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            homeViewModel.onEvent(HomeUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            homeViewModel.onEvent(HomeUIEvents.SignOff)
                        }
                    )
                }
            }
        }
    }
}