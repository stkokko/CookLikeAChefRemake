package com.cooking.cooklikeachef.presentation.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar

@Composable
fun MainScreen(
    navController: NavController
) {
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
            Image(
                painter = painterResource(id = R.drawable.home_image_background),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 10.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = innerPadding.calculateBottomPadding()
                )
        ) {
            Text(
                text = stringResource(id = R.string.welcome_to_appname),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier.align(alignment = Alignment.CenterStart)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter)
            ) {
                Text(
                    text = stringResource(id = R.string.latest_recipes), color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                //TODO LazyRow
            }
        }
    }
}