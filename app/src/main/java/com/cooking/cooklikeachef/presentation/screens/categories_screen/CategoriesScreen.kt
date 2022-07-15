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
                        headerLayoutFraction = 0.38f,
                        categoriesFontSize = 54.sp,
                        searchPlaceholderFontSize = 22.sp,
                        searchTextStyleFontSize = 22.sp,
                        searchModifier = Modifier
                            .width(380.dp)
                            .height(64.dp),
                        categoriesContainerFraction = 0.66f,
                        categoriesLeftColumnModifier = Modifier
                            .fillMaxHeight()
                            .width(200.dp),
                        categoriesRightColumnModifier = Modifier
                            .fillMaxHeight(fraction = 0.78f)
                            .width(200.dp),
                        categoryCardHeight = 170.dp,
                        categoryCardFontSize = 20.sp,
                        navController = navController,
                        categoriesViewModel = categoriesViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    // TODO
                }

                boxWithConstraintsScope.maxHeight > 600.dp -> {
                    CategoriesContent(
                        headerLayoutFraction = 0.38f,
                        categoriesFontSize = 44.sp,
                        searchPlaceholderFontSize = 20.sp,
                        searchTextStyleFontSize = 20.sp,
                        searchModifier = Modifier
                            .width(310.dp)
                            .height(56.dp),
                        categoriesContainerFraction = 0.62f,
                        categoriesLeftColumnModifier = Modifier
                            .fillMaxHeight()
                            .width(160.dp),
                        categoriesRightColumnModifier = Modifier
                            .fillMaxHeight(fraction = 0.74f)
                            .width(160.dp),
                        categoryCardHeight = 130.dp,
                        navController = navController,
                        categoriesViewModel = categoriesViewModel
                    )
                }
                else -> {
                    CategoriesContent(
                        headerLayoutFraction = 0.34f,
                        categoriesFontSize = 32.sp,
                        searchPlaceholderFontSize = 16.sp,
                        searchModifier = Modifier
                            .width(240.dp)
                            .height(52.dp),
                        categoriesContainerFraction = 0.7f,
                        categoriesLeftColumnModifier = Modifier
                            .fillMaxHeight()
                            .width(130.dp),
                        categoriesRightColumnModifier = Modifier
                            .fillMaxHeight(fraction = 0.7f)
                            .width(130.dp),
                        categoryCardHeight = 106.dp,
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
    headerLayoutFraction: Float,
    categoriesFontSize: TextUnit = TextUnit.Unspecified,
    searchPlaceholderFontSize: TextUnit = TextUnit.Unspecified,
    searchTextStyleFontSize: TextUnit = 16.sp,
    searchModifier: Modifier,
    categoriesContainerFraction: Float,
    categoriesLeftColumnModifier: Modifier,
    categoriesRightColumnModifier: Modifier,
    categoryCardHeight: Dp,
    categoryCardFontSize: TextUnit = 16.sp,
    navController: NavController,
    categoriesViewModel: CategoriesViewModel
) {
    val state = categoriesViewModel.state

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
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OptionsMenu(
                expandedOptionsMenu = state.value.displayOptionsMenu,
                openOptionsMenu = {
                    categoriesViewModel.onEvent(CategoriesUIEvents.DisplayOptionsMenu)
                },
                closeOptionsMenu = {
                    categoriesViewModel.onEvent(CategoriesUIEvents.OptionsMenuDismissed)
                }
            ) {
                categoriesViewModel.onEvent(CategoriesUIEvents.SignOff)
            }
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