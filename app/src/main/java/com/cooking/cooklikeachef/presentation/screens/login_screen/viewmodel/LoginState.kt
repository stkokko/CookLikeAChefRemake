package com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isResetPasswordDialogOpen: Boolean = false,
    val dialogEmail: String = "",
    val isDialogEmailValid: Boolean = false,
    val isResetPasswordSent: Boolean = false,
    val isLogInLoading: Boolean = false,
    val isResetPasswordLoading: Boolean = false,
    val isExitAppDialogOpen: Boolean = false,
    val errorMessageLoginForm: String = "",
    val errorMessageDialog: String = "",
    val isLoggedIn: Boolean = false,
)
