package com.cooking.cooklikeachef.presentation.screens.common_compoments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoginRegisterLayout(painter: Painter, content: @Composable () -> Unit) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val boxWithConstraintsScope = this
        when {
            boxWithConstraintsScope.maxHeight > 900.dp -> {
                LoginRegisterContainer(imageHeight = 340.dp, fraction = 0.75f, painter = painter) {
                    content()
                }
            }

            boxWithConstraintsScope.maxHeight > 700.dp -> {
                LoginRegisterContainer(imageHeight = 240.dp, fraction = 0.75f, painter = painter) {
                    content()
                }
            }

            boxWithConstraintsScope.maxHeight > 600.dp -> {
                LoginRegisterContainer(imageHeight = 240.dp, fraction = 0.7f, painter = painter) {
                    content()
                }
            }
            else -> {
                LoginRegisterContainer(imageHeight = 190.dp, fraction = 0.7f, painter = painter) {
                    content()
                }
            }
        }
    }

}

@Composable
private fun LoginRegisterContainer(
    imageHeight: Dp,
    fraction: Float,
    painter: Painter,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .height(imageHeight)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = fraction)
                .align(alignment = Alignment.BottomCenter)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 46.dp, topEnd = 46.dp)
                )
        ) {
            content()
        }
    }
}