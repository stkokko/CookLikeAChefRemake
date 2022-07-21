package com.cooking.cooklikeachef.presentation.screens.register_screen.viewmodel

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordValid: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val isExitAppDialogOpen: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isRegistered: Boolean = false,
)
