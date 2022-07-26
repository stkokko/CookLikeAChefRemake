package com.cooking.cooklikeachef.presentation.screens.recipe_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.common_compoments.RecipeBottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.view_model.RecipeViewModel
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.components.CustomCollapsingToolbar

@Composable
fun RecipeScreen(
    navController: NavController,
    recipeViewModel: RecipeViewModel = hiltViewModel()
) {
    val lazyScrollState = rememberLazyListState()

    Scaffold(topBar = {
        CustomCollapsingToolbar(lazyScrollState)
    }, bottomBar = {
        RecipeBottomNavigationBar(
            navController = navController
        )
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = lazyScrollState
        ) {
            items(count = 40) {
                Text(text = "Ingredients")
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