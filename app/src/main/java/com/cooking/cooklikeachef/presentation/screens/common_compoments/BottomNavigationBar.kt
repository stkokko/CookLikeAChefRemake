package com.cooking.cooklikeachef.presentation.screens.common_compoments

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
import okhttp3.internal.immutableListOf

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoot = navBackStackEntry?.destination?.route
    val mainValues = immutableListOf(Icons.Default.Home, R.string.home)
    val categoriesValues = immutableListOf(Icons.Default.Category, R.string.categories)
    val favouritesValues = immutableListOf(Icons.Default.Favorite, R.string.favourites)
    val screens = mapOf(
        Screens.Home.name to mainValues,
        Screens.Categories.name to categoriesValues,
        Screens.Favourites.name to favouritesValues
    )

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        backgroundColor = colorResource(
            id = R.color.general_color
        ),
        elevation = 6.dp
    ) {
        for (screen in screens) {
            BottomNavigationItem(
                selected = currentRoot == screen.key, onClick = {
                    navController.navigate(screen.key) {
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
                        imageVector = screen.value[0] as ImageVector,
                        modifier = Modifier.size(32.dp),
                        contentDescription = null
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                label = {
                    Text(text = stringResource(id = screen.value[1] as Int), fontSize = 14.sp)
                }
            )
        }
    }
}