package com.cooking.cooklikeachef.presentation.screens.comments_screen

import androidx.activity.compose.BackHandler
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.common_compoments.RecipeBottomNavigationBar

@Composable
fun CommentsScreen(navController: NavController) {
    Scaffold(bottomBar = {
        RecipeBottomNavigationBar(
            navController = navController
        )
    }) { innerPadding ->

        BackHandler {

        }
    }
}