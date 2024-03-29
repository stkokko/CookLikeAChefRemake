package com.cooking.cooklikeachef.presentation.screens.favourites_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cooking.cooklikeachef.domain.model.Recipe

@Composable
fun FavouriteRecipesCard(favouriteRecipe: Recipe, onCardClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(4.dp)
            .clickable {
                onCardClicked(favouriteRecipe.id)
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(0.5f))) {
            AsyncImage(
                model = favouriteRecipe.imageURL,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = 0.7f,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = favouriteRecipe.name,
                fontSize = 22.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )

        }
    }
}