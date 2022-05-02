package com.cooking.cooklikeachef.presentation.screens.favourites_screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.main_screen.components.BottomNavigationBar

@Composable
fun FavouritesScreen(navController: NavController) {
    Text(text = "Favourites")
    Scaffold(bottomBar = {
        BottomNavigationBar(
            navController = navController
        )
    }) {

    }
}