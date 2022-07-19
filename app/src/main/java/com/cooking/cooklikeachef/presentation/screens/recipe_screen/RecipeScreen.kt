package com.cooking.cooklikeachef.presentation.screens.recipe_screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.common_compoments.RecipeBottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.view_model.RecipeViewModel

@Composable
fun RecipeScreen(
    navController: NavController,
    recipeViewModel: RecipeViewModel = hiltViewModel()
) {
    Scaffold(bottomBar = {
        RecipeBottomNavigationBar(
            navController = navController
        )
    }) { innerPadding ->


    }
}