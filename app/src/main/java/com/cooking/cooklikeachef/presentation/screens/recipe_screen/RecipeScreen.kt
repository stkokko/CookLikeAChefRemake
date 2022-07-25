package com.cooking.cooklikeachef.presentation.screens.recipe_screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.common_compoments.RecipeBottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.view_model.RecipeViewModel
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.ui.theme.Cherry

@OptIn(ExperimentalMotionApi::class)
@Composable
fun RecipeScreen(
    navController: NavController,
    recipeViewModel: RecipeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }
    val verticalScrollState = rememberScrollState()


    Scaffold(bottomBar = {
        RecipeBottomNavigationBar(
            navController = navController
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MotionLayout(
                motionScene = MotionScene(content = motionScene),
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                val boxProperties = motionProperties(id = "toolbar")
                val boxRoundedShape = RoundedCornerShape(
                    bottomStart = boxProperties.value.int("roundValue").dp,
                    bottomEnd = boxProperties.value.int("roundValue").dp
                )

                val imageProperties = motionProperties(id = "toolbar_image")
                val imageRoundedShape = RoundedCornerShape(
                    bottomStart = imageProperties.value.int("roundValue").dp,
                    bottomEnd = imageProperties.value.int("roundValue").dp
                )

                val titleProperties = motionProperties(id = "title")

                Box(
                    modifier = Modifier
                        .clip(boxRoundedShape)
                        .background(color = boxProperties.value.color("backgroundColor"))
                        .layoutId("toolbar")
                )

                Image(
                    painter = painterResource(id = R.drawable.recipe_image_example_2),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(imageRoundedShape)
                        .layoutId("toolbar_image")
                )

                Text(
                    text = "RECIPE NAME",
                    color = Color.White,
                    fontSize = titleProperties.value.fontSize("textSize"),
                    modifier = Modifier
                        .wrapContentSize()
                        .layoutId("title")
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

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId("content")
                        .verticalScroll(verticalScrollState) // TODO: scroll does not work
                ) {

                    Text(text = "Ingredients")
                    // TODO: all the Rows below would be replaced with
                    // TODO: LazyColumn for the list of ingredients
                    Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                        Icon(imageVector = Icons.Default.List, contentDescription = "")
                        Text(text = "Ingredient")
                    }
                    Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                        Icon(
                            imageVector = Icons.Default.FormatListBulleted,
                            contentDescription = ""
                        )
                        Text(text = "Ingredient")
                    }
                    Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                        Icon(
                            imageVector = Icons.Default.FormatListBulleted,
                            contentDescription = ""
                        )
                        Text(text = "Ingredient")
                    }
                    Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                        Icon(
                            imageVector = Icons.Default.FormatListBulleted,
                            contentDescription = ""
                        )
                        Text(text = "Ingredient")
                    }
                    Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                        Icon(
                            imageVector = Icons.Default.FormatListBulleted,
                            contentDescription = ""
                        )
                        Text(text = "Ingredient")
                    }
                    Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                        Icon(
                            imageVector = Icons.Default.FormatListBulleted,
                            contentDescription = ""
                        )
                        Text(text = "Ingredient")
                    }
                    Text(text = "Steps")
                    Text(
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                                "when an unknown printer took a galley of type and scrambled it to make a type " +
                                "specimen book. It has survived not only five centuries, but also the leap into " +
                                "electronic typesetting, remaining essentially unchanged. It was popularised in " +
                                "the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and" +
                                " more recently with desktop publishing software like Aldus PageMaker including " +
                                "versions of Lorem Ipsum."
                    )

                    Log.d(
                        "RecipeScreen",
                        "scroll state: ${verticalScrollState.value == verticalScrollState.maxValue}"
                    )
                }
            }
        }

    }
}