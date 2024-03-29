package com.cooking.cooklikeachef.presentation.screens.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.cooking.cooklikeachef.R
import coil.compose.AsyncImage
import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.presentation.ui.theme.Cherry

@Composable
fun LatestRecipesCard(
    modifier: Modifier,
    latestRecipe: Recipe? = null,
    textFontSize: TextUnit = TextUnit.Unspecified,
    isError: Boolean = false,
    onReadMoreClicked: ((String) -> Unit)? = null
) {
    Card(
        modifier = modifier,
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        if (!isError) {
            Row(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.46f)
                        .fillMaxHeight()
                ) {
                    AsyncImage(
                        model = latestRecipe?.imageURL,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = latestRecipe?.name ?: "",
                        fontSize = textFontSize,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.read_more),
                        fontSize = textFontSize,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier.clickable {
                            latestRecipe?.id?.let {
                                if (onReadMoreClicked != null) {
                                    onReadMoreClicked(it)
                                }
                            }
                        }
                    )
                }
            }
        } else {
            Icon(imageVector = Icons.Default.Error, tint = Cherry, contentDescription = null)
        }
    }
}