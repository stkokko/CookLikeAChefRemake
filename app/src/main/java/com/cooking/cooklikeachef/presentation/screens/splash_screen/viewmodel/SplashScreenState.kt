package com.cooking.cooklikeachef.presentation.screens.splash_screen.viewmodel

data class SplashScreenState(
    val isLoggedIn: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = false
)