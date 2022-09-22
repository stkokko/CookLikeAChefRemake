package com.cooking.cooklikeachef.presentation.screens.recipe_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.screens.common_compoments.RecipeBottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.view_model.RecipeViewModel
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.components.CustomCollapsingToolbar
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.events.RecipeUIEvents

@Composable
fun RecipeScreen(
    navController: NavController,
    recipeId: String,
    recipeViewModel: RecipeViewModel = hiltViewModel()
) {
    // TODO: check the !! (3 of them)
    val lazyScrollState = rememberLazyListState()
    val state = recipeViewModel.state

    if (state.value.recipe == null) {
        LaunchedEffect(Unit) {
            recipeViewModel.onEvent(RecipeUIEvents.GetRecipeById(recipeId))
        }
    } else {
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
            Scaffold(topBar = {
                CustomCollapsingToolbar(lazyScrollState, state.value.recipe!!)
            }, bottomBar = {
                RecipeBottomNavigationBar(
                    navController = navController
                )
            }) { innerPadding ->
                // TODO: get rid of column
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.9f)
                            .fillMaxHeight()
                            .animateContentSize(),
                        state = lazyScrollState
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(id = R.string.ingredients),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        itemsIndexed(state.value.recipe!!.ingredients) { index, ingredient ->
//                            if (index == 0) {
//                                Spacer(modifier = Modifier.height(16.dp))
//                                Text(
//                                    text = stringResource(id = R.string.ingredients),
//                                    fontSize = 18.sp,
//                                    fontWeight = FontWeight.Medium,
//                                    letterSpacing = 0.5.sp
//                                )
//                                Spacer(modifier = Modifier.height(10.dp))
//                            }
                            Text(
                                text = "${index + 1}. ${
                                    ingredient.name.trim().replaceFirstChar { it.uppercase() }
                                } (${ingredient.quantity})",
                                fontStyle = FontStyle.Italic
                            )
                            if (state.value.recipe!!.ingredients.size == index + 1)
                                Spacer(modifier = Modifier.height(16.dp))
                            else
                                Spacer(modifier = Modifier.height(2.dp))
                        }

                        item {
                            Text(
                                text = stringResource(id = R.string.steps),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.5.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = state.value.recipe!!.steps,
                                fontStyle = FontStyle.Italic
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                }
            }
        }
    }


//    BackHandler() {
//        // TODO: if u try to go back and u didn't navigate to comments screens through recipe screen
//        // TODO: then the default behavior is to return to home screen as its the only one in queue
//        // TODO: but if u navigated to comments screen 1st then it will return to comments screen
//        // TODO: when we just need to go back to home screen
//    }
}