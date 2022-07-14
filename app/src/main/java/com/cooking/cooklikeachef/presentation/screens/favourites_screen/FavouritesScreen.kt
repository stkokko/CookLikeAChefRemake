package com.cooking.cooklikeachef.presentation.screens.favourites_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.components.FavouriteRecipesCard
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.viewmodel.FavouritesViewModel

@Composable
fun FavouritesScreen(
    navController: NavController,
    favouritesViewModel: FavouritesViewModel = hiltViewModel()
) {
    val state = favouritesViewModel.state.value

    Scaffold(bottomBar = {
        BottomNavigationBar(
            navController = navController
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "SearchTextField")
            }
            Column(modifier = Modifier.fillMaxSize()) {
//                LazyColumn {
//                    items(state.favouriteRecipesList) { favouriteRecipe ->
//                        FavouriteRecipesCard(url = favouriteRecipe.imageURL, title = favouriteRecipe.name) {
//                            // TODO: navigate to RecipeScreen which is a
//                            // TODO: fragment in the original implementation
//                        }
//                    }
//                }
//                Log.d("FavouriteScreen", "favouriteRecipesList: ${state.favouriteRecipesList}")
            }
        }
    }
}