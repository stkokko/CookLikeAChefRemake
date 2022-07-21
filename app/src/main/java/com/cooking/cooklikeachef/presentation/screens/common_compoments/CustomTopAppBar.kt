package com.cooking.cooklikeachef.presentation.screens.common_compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    navigationIcon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                backgroundColor,
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
            )
    ) {
        if (navigationIcon != null) {
            Column(modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterStart)) {
                navigationIcon()
            }
        }
        if (title != null) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            ) {
                title()
            }
        }
        if (actions != null) {
            Column(modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterEnd)) {
                actions()
            }
        }
    }
}