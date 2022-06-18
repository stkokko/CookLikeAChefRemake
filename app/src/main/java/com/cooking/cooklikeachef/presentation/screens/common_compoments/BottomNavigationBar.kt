package com.cooking.cooklikeachef.presentation.screens.common_compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoot = navBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        backgroundColor = colorResource(
            id = R.color.general_color
        ),
        elevation = 6.dp
    ) {
        // TODO: create a composable for all 3 items
//        NavigationItem(
//            navController = navController,
//            currentRoot = currentRoot,
//            root = Screens.Main.name,
//            imageVector = Icons.Default.Home
//        )
//        NavigationItem(
//            navController = navController,
//            currentRoot = currentRoot,
//            root = Screens.Categories.name,
//            imageVector = Icons.Default.Category
//        )
//        NavigationItem(
//            navController = navController,
//            currentRoot = currentRoot,
//            root = Screens.Categories.name,
//            imageVector = Icons.Default.Favorite
//        )
        BottomNavigationItem(
            selected = currentRoot == Screens.Main.name, onClick = {
                navController.navigate(Screens.Main.name) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    modifier = Modifier.size(32.dp),
                    contentDescription = null
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            label = {
                Text(text = stringResource(id = R.string.home), fontSize = 14.sp)
            }
        )
        BottomNavigationItem(
            selected = currentRoot == Screens.Categories.name, onClick = {
                navController.navigate(Screens.Categories.name) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Category,
                    modifier = Modifier.size(32.dp),
                    contentDescription = null
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            label = {
                Text(text = stringResource(id = R.string.categories), fontSize = 14.sp)
            }
        )
        BottomNavigationItem(
            selected = currentRoot == Screens.Favourites.name, onClick = {
                navController.navigate(Screens.Favourites.name) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    modifier = Modifier.size(32.dp),
                    contentDescription = null
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            label = {
                Text(text = stringResource(id = R.string.favourites), fontSize = 14.sp)
            }
        )


    }
}

//@Composable
//fun NavigationItem(
//    navController: NavController,
//    currentRoot: String?,
//    root: String,
//    imageVector: ImageVector
//) {
//    BottomNavigationItem(
//        selected = currentRoot == root, onClick = {
//            navController.navigate(root) {
//                navController.graph.startDestinationRoute?.let { screen_route ->
//                    popUpTo(screen_route) {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
//                }
//            }
//        }, icon = {
//            Icon(
//                imageVector = imageVector,
//                modifier = Modifier.size(32.dp),
//                contentDescription = null
//            )
//        },
//        selectedContentColor = Color.White,
//        unselectedContentColor = Color.White.copy(alpha = 0.4f),
//        label = {
//            Text(text = stringResource(id = R.string.home), fontSize = 14.sp)
//        }
//    )
//}