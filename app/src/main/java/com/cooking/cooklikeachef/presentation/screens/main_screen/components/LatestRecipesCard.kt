package com.cooking.cooklikeachef.presentation.screens.main_screen.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cooking.cooklikeachef.R
import coil.compose.AsyncImage
import com.cooking.cooklikeachef.domain.model.Recipe

@Composable
fun LatestRecipesCard(url: String, title: String, onReadMoreClicked: (Recipe) -> Unit) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(120.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
            ) {
                AsyncImage(model = url, contentDescription = null, contentScale = ContentScale.Crop ,modifier = Modifier.fillMaxSize())
            }
            Column(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.read_more),
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.clickable { onReadMoreClicked }
                )
            }
        }
    }
}