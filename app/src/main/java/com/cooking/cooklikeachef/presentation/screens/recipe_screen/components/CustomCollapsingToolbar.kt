package com.cooking.cooklikeachef.presentation.screens.recipe_screen.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import coil.compose.AsyncImage
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.presentation.ui.theme.Cherry

@OptIn(ExperimentalMotionApi::class)
@Composable
fun CustomCollapsingToolbar(lazyScrollState: LazyListState, recipe: Recipe) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }

    val progress by animateFloatAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..4) 0f else 1f,
        tween(durationMillis = 1200)
    )
    val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..4) 280.dp else 64.dp,
        tween(durationMillis = 1200)
    )

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(motionHeight)
    ) {

        val toolbarProperties = motionProperties(id = "toolbar")
        val toolbarRoundedShape = RoundedCornerShape(
            bottomStart = toolbarProperties.value.int("roundValue").dp,
            bottomEnd = toolbarProperties.value.int("roundValue").dp
        )

        val recipeImageProperties = motionProperties(id = "recipe_image")
        val recipeImageRoundedShape = RoundedCornerShape(
            bottomStart = recipeImageProperties.value.int("roundValue").dp,
            bottomEnd = recipeImageProperties.value.int("roundValue").dp
        )

        val recipeNameProperties = motionProperties(id = "recipe_name")

        Box(
            modifier = Modifier
                .clip(toolbarRoundedShape)
                .background(color = toolbarProperties.value.color("backgroundColor"))
                .layoutId("toolbar")
        )

        AsyncImage(
            model = recipe.imageURL, contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(recipeImageRoundedShape)
                .layoutId("recipe_image")
        )

        Text(
            text = recipe.name,
            color = Color.White,
            fontWeight = FontWeight(recipeNameProperties.value.int("fontWeight")),
            fontSize = recipeNameProperties.value.fontSize("fontSize"),
            modifier = Modifier
                .wrapContentSize()
                .layoutId("recipe_name")
        )

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(32.dp)
                .layoutId("arrow_back")
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.White,
                contentDescription = null
            )
        }

        FloatingActionButton(
            onClick = { /*TODO*/ },
            backgroundColor = Color.White,
            modifier = Modifier.layoutId("fab")
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                tint = Cherry,
                contentDescription = null
            )
        }
    }
}