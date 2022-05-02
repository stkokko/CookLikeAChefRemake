package com.cooking.cooklikeachef.presentation.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.screens.main_screen.components.BottomNavigationBar

@Composable
fun MainScreen(
    navController: NavController
) {
    Scaffold(bottomBar = {
        BottomNavigationBar(
            navController = navController
        )
    }) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.home_image_background),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.welcome_to_appname),
                color = Color.White,
                fontSize = 26.sp,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter)
            ) {
                Text(
                    text = stringResource(id = R.string.latest_recipes), color = Color.White,
                    fontSize = 22.sp
                )
                //TODO LazyRow
            }
        }
    }
}