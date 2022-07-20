package com.cooking.cooklikeachef.presentation.screens.categories_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.categories_screen.components.Content
import com.cooking.cooklikeachef.presentation.screens.categories_screen.events.CategoriesUIEvents
import com.cooking.cooklikeachef.presentation.screens.categories_screen.viewmodel.CategoriesViewModel
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar

@Composable
fun CategoriesScreen(
    navController: NavController,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
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
                        searchFieldModifier = Modifier
                            .width(380.dp)
                            .height(64.dp),
                        categoriesLeftColumnModifier = Modifier
                            .fillMaxHeight()
                            .width(240.dp),
                        categoriesRightColumnModifier = Modifier
                            .fillMaxHeight(fraction = 0.76f)
                            .width(240.dp),
                        categoriesFontSize = 54.sp,
                        searchPlaceholderFontSize = 22.sp, // TODO when search results implemented check the size
                        searchTextStyleFontSize = 22.sp, // TODO same here for every screen
                        categoryCardHeight = 200.dp,
                        categoryCardFontSize = 22.sp,
                        optionsMenuIconSize = 38.dp,
                        optionsMenuDropdownWidth = 240.dp,
                        optionsMenuDropdownItemFontSize = 28.sp,
                        navController = navController,
                        state = categoriesViewModel.state,
                        eventDisplayOptionsMenu = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.SignOff)
                        },
                        eventSearchRecipeChanged = { recipe ->
                            categoriesViewModel.onEvent(
                                CategoriesUIEvents.SearchRecipeChanged(
                                    recipe
                                )
                            )
                        },
                        eventDismissSearchRecipeDropdown = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DismissSearchRecipeDropdown)
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    Content(
                        searchFieldModifier = Modifier
                            .width(350.dp)
                            .height(60.dp),
                        categoriesLeftColumnModifier = Modifier
                            .fillMaxHeight()
                            .width(200.dp),
                        categoriesRightColumnModifier = Modifier
                            .fillMaxHeight(fraction = 0.74f)
                            .width(200.dp),
                        categoriesFontSize = 46.sp,
                        searchPlaceholderFontSize = 22.sp,
                        searchTextStyleFontSize = 22.sp,
                        categoryCardHeight = 160.dp,
                        categoryCardFontSize = 20.sp,
                        optionsMenuIconSize = 34.dp,
                        optionsMenuDropdownWidth = 200.dp,
                        optionsMenuDropdownItemFontSize = 22.sp,
                        navController = navController,
                        state = categoriesViewModel.state,
                        eventDisplayOptionsMenu = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.SignOff)
                        },
                        eventSearchRecipeChanged = { recipe ->
                            categoriesViewModel.onEvent(
                                CategoriesUIEvents.SearchRecipeChanged(
                                    recipe
                                )
                            )
                        },
                        eventDismissSearchRecipeDropdown = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DismissSearchRecipeDropdown)
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 620.dp -> {
                    Content(
                        headerLayoutFraction = 0.40f,
                        categoriesContainerFraction = 0.64f,
                        searchFieldModifier = Modifier
                            .width(270.dp)
                            .height(56.dp),
                        categoriesLeftColumnModifier = Modifier
                            .fillMaxHeight()
                            .width(140.dp),
                        categoriesRightColumnModifier = Modifier
                            .fillMaxHeight(fraction = 0.74f)
                            .width(140.dp),
                        categoriesFontSize = 36.sp,
                        searchPlaceholderFontSize = 20.sp,
                        searchTextStyleFontSize = 20.sp,
                        categoryCardHeight = 120.dp,
                        optionsMenuIconSize = 26.dp,
                        optionsMenuDropdownWidth = 170.dp,
                        optionsMenuDropdownItemFontSize = 18.sp,
                        navController = navController,
                        state = categoriesViewModel.state,
                        eventDisplayOptionsMenu = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.SignOff)
                        },
                        eventSearchRecipeChanged = { recipe ->
                            categoriesViewModel.onEvent(
                                CategoriesUIEvents.SearchRecipeChanged(
                                    recipe
                                )
                            )
                        },
                        eventDismissSearchRecipeDropdown = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DismissSearchRecipeDropdown)
                        }
                    )
                }
                else -> {
                    Content(
                        headerLayoutFraction = 0.34f,
                        categoriesContainerFraction = 0.68f,
                        searchFieldModifier = Modifier
                            .width(240.dp)
                            .height(52.dp),
                        categoriesLeftColumnModifier = Modifier
                            .fillMaxHeight()
                            .width(120.dp),
                        categoriesRightColumnModifier = Modifier
                            .fillMaxHeight(fraction = 0.7f)
                            .width(120.dp),
                        categoriesFontSize = 26.sp,
                        navController = navController,
                        state = categoriesViewModel.state,
                        eventDisplayOptionsMenu = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DisplayOptionsMenu)
                        },
                        eventDismissOptionsMenu = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DismissOptionsMenu)
                        },
                        eventContactUs = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.ContactUs(context))
                        },
                        eventSignOff = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.SignOff)
                        },
                        eventSearchRecipeChanged = { recipe ->
                            categoriesViewModel.onEvent(
                                CategoriesUIEvents.SearchRecipeChanged(
                                    recipe
                                )
                            )
                        },
                        eventDismissSearchRecipeDropdown = {
                            categoriesViewModel.onEvent(CategoriesUIEvents.DismissSearchRecipeDropdown)
                        }
                    )
                }
            }
        }
    }
}