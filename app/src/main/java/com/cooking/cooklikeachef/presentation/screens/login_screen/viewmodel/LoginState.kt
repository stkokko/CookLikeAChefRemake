package com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel

data class LoginState(
    val isLoggedIn: Boolean = false,
    val errorMessage: String = "",
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val isPasswordVisible: Boolean = false,
    val openDialog: Boolean = false,
    val isLoading: Boolean = false
)
