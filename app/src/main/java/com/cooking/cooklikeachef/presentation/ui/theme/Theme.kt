package com.cooking.cooklikeachef.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.cooking.cooklikeachef.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Cherry,
    primaryVariant = DarkCherry,
    secondary = SkyBlue
)

private val LightColorPalette = lightColors(
    primary = Cherry,
    primaryVariant = DarkCherry,
    secondary = SkyBlue

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CookLikeAChefTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUIController = rememberSystemUiController()
    systemUIController.setSystemBarsColor(
        color = colorResource(id = R.color.general_color)
    )
    systemUIController.setNavigationBarColor(
        color = colorResource(id = R.color.general_color)
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}