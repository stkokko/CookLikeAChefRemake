package com.cooking.cooklikeachef.presentation.screens.recipe_screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.FloatingActionButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomTopAppBar
import com.cooking.cooklikeachef.presentation.screens.common_compoments.RecipeBottomNavigationBar
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.view_model.RecipeViewModel
import com.cooking.cooklikeachef.presentation.ui.theme.LightCherry
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.ui.theme.Cherry
import me.onebone.toolbar.*

@Composable
fun RecipeScreen(
    navController: NavController,
    recipeViewModel: RecipeViewModel = hiltViewModel()
) {
    val state = rememberCollapsingToolbarScaffoldState()
    val progress = state.toolbarState.progress
    val fontSize = (18 + (30 - 18) * progress).sp

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                // TODO: update favourite recipes and state to change the icon
            },
            backgroundColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                tint = Cherry,
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )
        }
    }, bottomBar = {
        RecipeBottomNavigationBar(
            navController = navController
        )
    }) { innerPadding ->
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .alpha(if (fontSize.value == 18f) 1f else 0f)
                        .background(color = LightCherry)
                        .pin()
                )

                IconButton(
                    onClick = {
                        // TODO: go to previous screen
                        Log.d("RecipeScreen", "ArrowBack clicked")
                    }, modifier = Modifier
                        .height(64.dp)
                        .pin()
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "Arrow Back",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }

                Box(
                    modifier = if (fontSize.value == 18f) {
                        Modifier
                            .fillMaxWidth()
//                            .background(color = LightCherry)
                            .height(64.dp)
                            .padding(
                                start = if (fontSize.value == 18f) 0.dp else 16.dp
                            )
                            .road(
                                whenExpanded = Alignment.BottomStart,
                                whenCollapsed = Alignment.Center
                            )
                    } else {
                        Modifier
                            .height(64.dp)
                            .padding(
                                start = if (fontSize.value == 18f) 0.dp else 16.dp
                            )
                            .road(
                                whenExpanded = Alignment.BottomStart,
                                whenCollapsed = Alignment.Center
                            )
                    }
                ) {
                    Text(
                        text = "Recipe Title",
                        color = Color.Black,
                        fontSize = fontSize,
                        letterSpacing = 1.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.recipe_image_example),
                    contentDescription = "Recipe Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.4f)
                        .alpha(if (fontSize.value == 18f) 0f else 0.8f)
                        .parallax(0.2f)
                )
//                CollapsedToolbar(
//                    modifier = Modifier
//                        .alpha(if (fontSize.value == 18f) 1f else 0f)
//                        .pin(),
//                    fontSize = fontSize
//                )
//
//                ExpandedToolbar(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight(fraction = 0.4f)
//                        .alpha(if (fontSize.value == 18f) 0f else 1f)
//                        .road(
//                            whenCollapsed = Alignment.CenterStart,
//                            whenExpanded = Alignment.BottomStart
//                        ), fontSize = fontSize
//                )
            }
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = "Ingredients")
                // TODO: all the Rows below would be replaced with
                // TODO: LazyColumn for the list of ingredients
                Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                    Icon(imageVector = Icons.Default.List, contentDescription = "")
                    Text(text = "Ingredient")
                }
                Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                    Icon(imageVector = Icons.Default.FormatListBulleted, contentDescription = "")
                    Text(text = "Ingredient")
                }
                Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                    Icon(imageVector = Icons.Default.FormatListBulleted, contentDescription = "")
                    Text(text = "Ingredient")
                }
                Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                    Icon(imageVector = Icons.Default.FormatListBulleted, contentDescription = "")
                    Text(text = "Ingredient")
                }
                Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                    Icon(imageVector = Icons.Default.FormatListBulleted, contentDescription = "")
                    Text(text = "Ingredient")
                }
                Row(modifier = Modifier.fillMaxWidth(fraction = 0.8f)) {
                    Icon(imageVector = Icons.Default.FormatListBulleted, contentDescription = "")
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
            }
        }
    }
}

@Composable
fun CollapsedToolbar(modifier: Modifier, fontSize: TextUnit) {
    CustomTopAppBar(
        modifier = modifier,
        backgroundColor = LightCherry,
        navigationIcon = {
            IconButton(onClick = {
                // TODO: go to previous screen
                Log.d("RecipeScreen", "ArrowBack clicked")
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.Black,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        title = {
            Text(
                text = "Recipe Title",
                color = Color.Black,
                fontSize = fontSize,
                letterSpacing = 1.sp
            )
        })
}

@Composable
fun ExpandedToolbar(modifier: Modifier, fontSize: TextUnit) {
    // TODO: AsyncImage(model =, contentDescription =)
    ConstraintLayout(
        modifier = modifier
    ) {
        val (recipe_image, recipe_title, fab) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.recipe_image_example),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(recipe_image) {
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                }
        )

        Text(
            text = "Recipe Title",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            letterSpacing = 1.sp,
            modifier = Modifier
                .constrainAs(recipe_title) {
                    start.linkTo(anchor = parent.start, margin = 16.dp)
                    bottom.linkTo(anchor = parent.bottom, margin = 16.dp)
                }
        )

        FloatingActionButton(
            onClick = {
                // TODO: update favourite recipes and state to change the icon
            },
            backgroundColor = Color.White,
            modifier = Modifier
                .constrainAs(fab) {
                    end.linkTo(anchor = parent.end, margin = 20.dp)
                    top.linkTo(anchor = parent.bottom)
                    bottom.linkTo(anchor = parent.bottom)
                }
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                tint = Cherry,
                contentDescription = "",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}