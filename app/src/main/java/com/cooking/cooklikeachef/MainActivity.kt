package com.cooking.cooklikeachef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cooking.cooklikeachef.presentation.navigation.Navigation
import com.cooking.cooklikeachef.presentation.ui.theme.CookLikeAChefTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookLikeAChefTheme {
                Navigation()
            }
        }
    }
}