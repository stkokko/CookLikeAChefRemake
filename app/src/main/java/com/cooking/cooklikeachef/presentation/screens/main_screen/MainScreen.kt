package com.cooking.cooklikeachef.presentation.screens.main_screen

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.main_screen.components.LatestRecipesCard

@Composable
fun MainScreen(
    navController: NavController
) {
    LatestRecipesCard(
        url = "https://i.picsum.photos/id/855/200/300.jpg?hmac=2aBMcUWlYEynKymdtTjCpwCpl2v_ELuWkkmOeWbjqa0",
        title = "Burger",
        onReadMoreClicked = {}
    )
}