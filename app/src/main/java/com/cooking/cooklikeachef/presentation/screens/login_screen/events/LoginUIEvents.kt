package com.cooking.cooklikeachef.presentation.screens.login_screen.events

sealed class LoginUIEvents {
    class EmailChanged(val email: String) : LoginUIEvents()
    class PasswordChanged(val password: String) : LoginUIEvents()
    class SignIn(val email: String, val password: String) : LoginUIEvents()
    object ShowPasswordClick : LoginUIEvents()
    object OpenDialogClicked : LoginUIEvents()
    object DialogDismissed : LoginUIEvents()
}
