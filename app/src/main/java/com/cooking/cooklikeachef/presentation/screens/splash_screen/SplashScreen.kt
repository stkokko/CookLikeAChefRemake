package com.cooking.cooklikeachef.presentation.screens.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(key1 = true) {
        delay(3500L)
        navController.navigate(Screens.Login.name) {
            popUpTo(Screens.Splash.name) {
                inclusive = true
            }
        }
    }

    val compositionSplash by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash))
    val progressSplash by animateLottieCompositionAsState(
        composition = compositionSplash,
        iterations = LottieConstants.IterateForever
    )
    val compositionLoad by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    val progressLoad by animateLottieCompositionAsState(
        composition = compositionLoad,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.general_color)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        LottieAnimation(
            composition = compositionSplash,
            progress = progressSplash,
            modifier = Modifier.height(200.dp)
        )

        LottieAnimation(
            composition = compositionLoad,
            progress = progressLoad,
            modifier = Modifier.height(78.dp)
        )
    }
}