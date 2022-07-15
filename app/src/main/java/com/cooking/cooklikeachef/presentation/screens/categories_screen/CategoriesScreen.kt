package com.cooking.cooklikeachef.presentation.screens.categories_screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens
import com.cooking.cooklikeachef.presentation.screens.categories_screen.events.CategoriesUIEvents
import com.cooking.cooklikeachef.presentation.screens.categories_screen.viewmodel.CategoriesViewModel
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.common_compoments.OptionsMenu
import com.cooking.cooklikeachef.presentation.ui.theme.LightCherry

@Composable
fun CategoriesScreen(
    navController: NavController,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {
    Scaffold(bottomBar = {
        // TODO should we adjust somehow the size's of the bottom navigation bar too?
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
                    CategoriesContent(
                        searchModifier = Modifier
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
                        categoriesViewModel = categoriesViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    CategoriesContent(
                        searchModifier = Modifier
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
                        categoriesViewModel = categoriesViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 620.dp -> {
                    CategoriesContent(
                        headerLayoutFraction = 0.40f,
                        categoriesContainerFraction = 0.64f,
                        searchModifier = Modifier
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
                        optionsMenuDropdownWidth = 160.dp,
                        optionsMenuDropdownItemFontSize = 18.sp,
                        navController = navController,
                        categoriesViewModel = categoriesViewModel
                    )
                }
                else -> {
                    CategoriesContent(
                        headerLayoutFraction = 0.34f,
                        categoriesContainerFraction = 0.68f,
                        searchModifier = Modifier
                            .width(240.dp)
                            .height(52.dp),
                        categoriesLeftColumnModifier = Modifier
                            .fillMaxHeight()
                            .width(120.dp),
                        categoriesRightColumnModifier = Modifier
                            .fillMaxHeight(fraction = 0.7f)
                            .width(120.dp),
                        categoriesFontSize = 26.sp,
                        searchPlaceholderFontSize = 16.sp,
                        navController = navController,
                        categoriesViewModel = categoriesViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun CategoriesContent(
    headerLayoutFraction: Float = 0.34f,
    categoriesContainerFraction: Float = 0.7f,
    searchModifier: Modifier,
    categoriesLeftColumnModifier: Modifier,
    categoriesRightColumnModifier: Modifier,
    categoriesFontSize: TextUnit = TextUnit.Unspecified,
    searchPlaceholderFontSize: TextUnit = TextUnit.Unspecified,
    searchTextStyleFontSize: TextUnit = 16.sp,
    categoryCardHeight: Dp = 100.dp,
    categoryCardFontSize: TextUnit = 16.sp,
    optionsMenuIconSize: Dp = 24.dp,
    optionsMenuDropdownWidth: Dp = 146.dp,
    optionsMenuDropdownItemFontSize: TextUnit = 16.sp,
    navController: NavController,
    categoriesViewModel: CategoriesViewModel
) {
    val state = categoriesViewModel.state

    // TODO make it more readable

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
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = headerLayoutFraction)
                    .background(
                        color = LightCherry,
                        shape = RoundedCornerShape(
                            bottomStart = 26.dp,
                            bottomEnd = 26.dp
                        )
                    )
                    .padding(bottom = 16.dp)
            ) {
                OptionsMenu(
                    expandedOptionsMenu = state.value.displayOptionsMenu,
                    iconSize = optionsMenuIconSize,
                    dropdownMenuWidth = optionsMenuDropdownWidth,
                    dropdownItemFontSize = optionsMenuDropdownItemFontSize,
                    openOptionsMenu = {
                        categoriesViewModel.onEvent(CategoriesUIEvents.DisplayOptionsMenu)
                    },
                    closeOptionsMenu = {
                        categoriesViewModel.onEvent(CategoriesUIEvents.OptionsMenuDismissed)
                    }
                ) {
                    categoriesViewModel.onEvent(CategoriesUIEvents.SignOff)
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.categories),
                        fontSize = categoriesFontSize,
                        letterSpacing = 8.sp,
                        color = Color.White,
                        modifier = Modifier.offset(y = (-12).dp) // TODO
                    )
                    OutlinedTextField(
                        value = state.value.searchRecipe,
                        onValueChange = { searchRecipe ->
                            categoriesViewModel.onEvent(
                                CategoriesUIEvents.SearchRecipeChanged(
                                    searchRecipe.trim()
                                )
                            )
                        },
                        placeholder = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(
                                    text = stringResource(id = R.string.search),
                                    color = Color.LightGray,
                                    fontSize = searchPlaceholderFontSize,
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
                        // TODO
//                keyboardOptions = KeyboardOptions(
//                    imeAction = ImeAction.Go,
//                    keyboardType = KeyboardType.Text,
//                    autoCorrect = true
//                ),
//                keyboardActions = KeyboardActions(onGo = {
//                    categoriesViewModel.onEvent(CategoriesUIEvents.SearchRecipeResults(state.value.searchRecipe))
//                }),
                        shape = RoundedCornerShape(26.dp),
                        textStyle = TextStyle(fontSize = searchTextStyleFontSize),
                        modifier = searchModifier
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = categoriesContainerFraction)
                    .align(alignment = Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = categoriesLeftColumnModifier
                ) {
                    CategoryCard(
                        drawable = R.drawable.brunch_icon,
                        categoryName = R.string.brunch,
                        cardHeight = categoryCardHeight,
                        fontSize = categoryCardFontSize
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CategoryCard(
                        drawable = R.drawable.main_dishes_icon,
                        categoryName = R.string.main_dishes,
                        cardHeight = categoryCardHeight,
                        fontSize = categoryCardFontSize
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CategoryCard(
                        drawable = R.drawable.desserts_icon,
                        categoryName = R.string.desserts,
                        cardHeight = categoryCardHeight,
                        fontSize = categoryCardFontSize
                    ) {

                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = categoriesRightColumnModifier
                ) {
                    CategoryCard(
                        drawable = R.drawable.salads_icon,
                        categoryName = R.string.salads,
                        cardHeight = categoryCardHeight,
                        fontSize = categoryCardFontSize
                    ) {

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    CategoryCard(
                        drawable = R.drawable.burgers_icon,
                        categoryName = R.string.burgers,
                        cardHeight = categoryCardHeight,
                        fontSize = categoryCardFontSize
                    ) {

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
                popUpTo(Screens.Categories.name) {
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    @DrawableRes drawable: Int,
    @StringRes categoryName: Int,
    cardHeight: Dp,
    fontSize: TextUnit = 16.sp,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(cardHeight)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        backgroundColor = Color.White,
        elevation = 3.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.7f)
            )
            Text(
                text = stringResource(id = categoryName),
                fontWeight = FontWeight.Medium,
                fontSize = fontSize
            )
        }
    }
}