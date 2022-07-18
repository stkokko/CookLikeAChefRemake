package com.cooking.cooklikeachef.presentation.screens.register_screen.events

sealed class RegisterUIEvents {
    class EmailChanged(val email: String) : RegisterUIEvents()
    class PasswordChanged(val password: String) : RegisterUIEvents()
    class ConfirmPasswordChanged(val password: String, val confirmPassword: String) : RegisterUIEvents()
    class CreateUser(val email: String, val password: String) : RegisterUIEvents()
    object ShowPasswordClick : RegisterUIEvents()
    object ShowConfirmPasswordClick : RegisterUIEvents()
    object OpenExitAppDialog: RegisterUIEvents()
    object DismissExitAppDialog: RegisterUIEvents()
}
