package com.cooking.cooklikeachef.presentation.screens.favourites_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cooking.cooklikeachef.domain.model.Recipe

@Composable
fun FavouriteRecipesCard(url: String, title: String, onCardClicked: (Recipe) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(4.dp)
            .clickable {
                onCardClicked
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.6f,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = title,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

        }
    }
}