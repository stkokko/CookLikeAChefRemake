package com.cooking.cooklikeachef.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cooking.cooklikeachef.presentation.screens.categories_screen.CategoriesScreen
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.FavouritesScreen
import com.cooking.cooklikeachef.presentation.screens.main_screen.MainScreen
import com.cooking.cooklikeachef.presentation.screens.splash_screen.SplashScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Splash.name) {

        composable(route = Screens.Splash.name) {
            SplashScreen(navController = navController)
        }

        composable(route = Screens.Main.name) {
            MainScreen(navController = navController)
        }

        composable(route = Screens.Categories.name) {
            CategoriesScreen(navController = navController)
        }

        composable(route = Screens.Favourites.name) {
            FavouritesScreen(navController = navController)
        }

    }

}