package com.cooking.cooklikeachef.presentation.screens.common_compoments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = false,
    isLoading: Boolean = false,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            disabledBackgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.5f)
        )
    ) {
        if (!isLoading)
            Text(text = text)
        else {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }

}