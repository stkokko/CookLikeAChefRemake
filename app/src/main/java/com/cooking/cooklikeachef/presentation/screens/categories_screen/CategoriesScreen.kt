package com.cooking.cooklikeachef.presentation.screens.categories_screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cooking.cooklikeachef.R
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cooking.cooklikeachef.presentation.screens.categories_screen.components.CategoriesLayout
import com.cooking.cooklikeachef.presentation.screens.common_compoments.BottomNavigationBar
import com.cooking.cooklikeachef.presentation.ui.theme.LightCherry

@Composable
fun CategoriesScreen(navController: NavController) {
    Text(text = "Categories")
    Scaffold(bottomBar = {
        BottomNavigationBar(
            navController = navController
        )
    }) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            var searchValue by remember {
                mutableStateOf("")
            }
            val boxWithConstraintsScope = this
            when {
                boxWithConstraintsScope.maxHeight > 900.dp -> {
//                    CategoriesLayout(searchValue = searchValue)
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(fraction = 0.35f)
                                .background(
                                    color = LightCherry,
                                    shape = RoundedCornerShape(
                                        bottomStart = 26.dp,
                                        bottomEnd = 26.dp
                                    )
                                )
                                .padding(bottom = 16.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.categories),
                                fontSize = 32.sp,
                                letterSpacing = 8.sp,
                                color = Color.White
                            )
                            OutlinedTextField(
                                value = searchValue,
                                onValueChange = { searchValue = it },
                                placeholder = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.search),
                                            color = Color.LightGray,
                                            fontSize = 16.sp,
                                            modifier = Modifier
                                                .fillMaxSize()
                                        )
                                    }
                                },
                                singleLine = true,
                                maxLines = 1,
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Go,
                                    keyboardType = KeyboardType.Text,
                                    autoCorrect = true
                                ),
                                keyboardActions = KeyboardActions(onGo = {
                                    // TODO
                                }),
                                shape = RoundedCornerShape(26.dp),
                                modifier = Modifier
                                    .width(240.dp)
                                    .height(52.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(fraction = 0.7f)
                                .align(alignment = Alignment.BottomCenter),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(145.dp)
                            ) {
                                CategoryCard(
                                    drawable = R.drawable.brunch_icon,
                                    categoryName = R.string.brunch
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                CategoryCard(
                                    drawable = R.drawable.main_dishes_icon,
                                    categoryName = R.string.main_dishes
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                CategoryCard(
                                    drawable = R.drawable.desserts_icon,
                                    categoryName = R.string.desserts
                                ) {

                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(fraction = 0.7f)
                                    .width(145.dp)
                            ) {
                                CategoryCard(
                                    drawable = R.drawable.salads_icon,
                                    categoryName = R.string.salads
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                CategoryCard(
                                    drawable = R.drawable.burgers_icon,
                                    categoryName = R.string.burgers
                                ) {

                                }
                            }
                        }
                    }
                }
                boxWithConstraintsScope.maxHeight > 600.dp -> {
//                    CategoriesLayout(searchValue = searchValue)
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(fraction = 0.35f)
                                .background(
                                    color = LightCherry,
                                    shape = RoundedCornerShape(
                                        bottomStart = 26.dp,
                                        bottomEnd = 26.dp
                                    )
                                )
                                .padding(bottom = 16.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.categories),
                                fontSize = 32.sp,
                                letterSpacing = 8.sp,
                                color = Color.White
                            )
                            OutlinedTextField(
                                value = searchValue,
                                onValueChange = { searchValue = it },
                                placeholder = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.search),
                                            color = Color.LightGray,
                                            fontSize = 16.sp,
                                            modifier = Modifier
                                                .fillMaxSize()
                                        )
                                    }
                                },
                                singleLine = true,
                                maxLines = 1,
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Go,
                                    keyboardType = KeyboardType.Text,
                                    autoCorrect = true
                                ),
                                keyboardActions = KeyboardActions(onGo = {
                                    // TODO
                                }),
                                shape = RoundedCornerShape(26.dp),
                                modifier = Modifier
                                    .width(240.dp)
                                    .height(52.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(fraction = 0.7f)
                                .align(alignment = Alignment.BottomCenter),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(145.dp)
                            ) {
                                CategoryCard(
                                    drawable = R.drawable.brunch_icon,
                                    categoryName = R.string.brunch
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                CategoryCard(
                                    drawable = R.drawable.main_dishes_icon,
                                    categoryName = R.string.main_dishes
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                CategoryCard(
                                    drawable = R.drawable.desserts_icon,
                                    categoryName = R.string.desserts
                                ) {

                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(fraction = 0.7f)
                                    .width(145.dp)
                            ) {
                                CategoryCard(
                                    drawable = R.drawable.salads_icon,
                                    categoryName = R.string.salads
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                CategoryCard(
                                    drawable = R.drawable.burgers_icon,
                                    categoryName = R.string.burgers
                                ) {

                                }
                            }
                        }
                    }
                }
                else -> {
//                    CategoriesLayout(searchValue = searchValue)
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(fraction = 0.35f)
                                .background(
                                    color = LightCherry,
                                    shape = RoundedCornerShape(
                                        bottomStart = 26.dp,
                                        bottomEnd = 26.dp
                                    )
                                )
                                .padding(bottom = 16.dp),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.categories),
                                fontSize = 32.sp,
                                letterSpacing = 8.sp,
                                color = Color.White
                            )
                            OutlinedTextField(
                                value = searchValue,
                                onValueChange = { searchValue = it },
                                placeholder = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.search),
                                            color = Color.LightGray,
                                            fontSize = 16.sp,
                                            modifier = Modifier
                                                .fillMaxSize()
                                        )
                                    }
                                },
                                singleLine = true,
                                maxLines = 1,
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Go,
                                    keyboardType = KeyboardType.Text,
                                    autoCorrect = true
                                ),
                                keyboardActions = KeyboardActions(onGo = {
                                    // TODO
                                }),
                                shape = RoundedCornerShape(26.dp),
                                modifier = Modifier
                                    .width(240.dp)
                                    .height(52.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(fraction = 0.7f)
                                .align(alignment = Alignment.BottomCenter),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(145.dp)
                            ) {
                                CategoryCard(
                                    drawable = R.drawable.brunch_icon,
                                    categoryName = R.string.brunch
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                CategoryCard(
                                    drawable = R.drawable.main_dishes_icon,
                                    categoryName = R.string.main_dishes
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                CategoryCard(
                                    drawable = R.drawable.desserts_icon,
                                    categoryName = R.string.desserts
                                ) {

                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(fraction = 0.7f)
                                    .width(145.dp)
                            ) {
                                CategoryCard(
                                    drawable = R.drawable.salads_icon,
                                    categoryName = R.string.salads
                                ) {

                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                CategoryCard(
                                    drawable = R.drawable.burgers_icon,
                                    categoryName = R.string.burgers
                                ) {

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCard(@DrawableRes drawable: Int, @StringRes categoryName: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        backgroundColor = Color.White,
        elevation = 3.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.75f)
            )
            Text(
                text = stringResource(id = categoryName),
                fontWeight = FontWeight.Medium
            )
        }
    }
}