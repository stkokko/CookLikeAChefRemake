package com.cooking.cooklikeachef.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cooking.cooklikeachef.presentation.screens.categories_screen.CategoriesScreen
import com.cooking.cooklikeachef.presentation.screens.comments_screen.CommentsScreen
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.FavouritesScreen
import com.cooking.cooklikeachef.presentation.screens.login_screen.LoginScreen
import com.cooking.cooklikeachef.presentation.screens.home_screen.MainScreen
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.RecipeScreen
import com.cooking.cooklikeachef.presentation.screens.register_screen.RegisterScreen
import com.cooking.cooklikeachef.presentation.screens.splash_screen.SplashScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Splash.name) {

        composable(route = Screens.Splash.name) {
            SplashScreen(navController = navController)
        }

        composable(route = Screens.Login.name) {
            LoginScreen(navController = navController)
        }

        composable(route = Screens.Register.name) {
            RegisterScreen(navController = navController)
        }

        composable(route = Screens.Home.name) {
            MainScreen(navController = navController)
        }

        composable(route = Screens.Categories.name) {
            CategoriesScreen(navController = navController)
        }

        composable(route = Screens.Favourites.name) {
            FavouritesScreen(navController = navController)
        }

        composable(route = Screens.Recipe.name) {
            val recipeId = navController.previousBackStackEntry?.savedStateHandle?.get<String>("id")
            recipeId?.let { id -> RecipeScreen(navController = navController, recipeId = id) }
        }

        composable(route = Screens.Comments.name) {
            CommentsScreen(navController = navController)
        }
    }

}