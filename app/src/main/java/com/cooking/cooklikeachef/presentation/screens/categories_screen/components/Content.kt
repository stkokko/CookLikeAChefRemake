package com.cooking.cooklikeachef.presentation.screens.categories_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens
import com.cooking.cooklikeachef.presentation.screens.categories_screen.viewmodel.CategoriesState
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomTopAppBar
import com.cooking.cooklikeachef.presentation.screens.common_compoments.OptionsMenu
import com.cooking.cooklikeachef.presentation.screens.common_compoments.RecipeResultDropdown
import com.cooking.cooklikeachef.presentation.ui.theme.LightCherry

@Composable
fun Content(
    scaffoldState: ScaffoldState,
    headerLayoutFraction: Float = 0.34f,
    categoriesContainerFraction: Float = 0.7f,
    searchFieldModifier: Modifier,
    categoriesLeftColumnModifier: Modifier,
    categoriesRightColumnModifier: Modifier,
    categoriesFontSize: TextUnit = TextUnit.Unspecified,
    searchPlaceholderFontSize: TextUnit = 16.sp,
    searchTextStyleFontSize: TextUnit = 16.sp,
    categoryCardHeight: Dp = 100.dp,
    categoryCardFontSize: TextUnit = 16.sp,
    optionsMenuIconSize: Dp = 24.dp,
    optionsMenuDropdownWidth: Dp = 146.dp,
    optionsMenuDropdownItemFontSize: TextUnit = 16.sp,
    navController: NavController,
    state: State<CategoriesState>,
    eventDisplayOptionsMenu: () -> Unit,
    eventDismissOptionsMenu: () -> Unit,
    eventContactUs: () -> Unit,
    eventSignOff: () -> Unit,
    eventSearchRecipeChanged: (String) -> Unit,
    eventDismissSearchRecipeDropdown: () -> Unit
) {
    // TODO make it more readable

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
        Box(modifier = Modifier.fillMaxSize()) {
            ConstraintLayout(
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
                val (customTopAppBar, text, outlinedTextField, dropdown) = createRefs()
                CustomTopAppBar(modifier = Modifier.constrainAs(customTopAppBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
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
                Text(
                    text = stringResource(id = R.string.categories),
                    fontSize = categoriesFontSize,
                    letterSpacing = 8.sp,
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(text) {
                            top.linkTo(customTopAppBar.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
                OutlinedTextField(
                    value = state.value.searchRecipe,
                    onValueChange = { searchRecipe ->
                        eventSearchRecipeChanged(searchRecipe.trim())
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
                    shape = RoundedCornerShape(26.dp),
                    textStyle = TextStyle(fontSize = searchTextStyleFontSize),
                    modifier = searchFieldModifier.constrainAs(outlinedTextField) {
                        top.linkTo(text.bottom, margin = 12.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
                if (state.value.isSearchRecipeDropdownExpanded) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .constrainAs(dropdown) {
                                top.linkTo(outlinedTextField.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .background(Color.Black)
                    ) {
                        RecipeResultDropdown(
                            isDropdownExpanded = state.value.isSearchRecipeDropdownExpanded,
                            recipes = state.value.recipes.filter { it.name.contains(state.value.searchRecipe) }
                        ) {
                            eventDismissSearchRecipeDropdown()
                        }
                    }
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
                popUpTo(Screens.Categories.name) {
                    inclusive = true
                }
            }
        }
    }
}