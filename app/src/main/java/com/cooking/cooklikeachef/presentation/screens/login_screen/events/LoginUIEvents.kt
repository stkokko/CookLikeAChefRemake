package com.cooking.cooklikeachef.presentation.screens.login_screen.events

sealed class LoginUIEvents {
    class EmailChanged(val email: String) : LoginUIEvents()
    class PasswordChanged(val password: String) : LoginUIEvents()
    class DialogEmailChanged(val dialogEmail: String) : LoginUIEvents()
    class SignIn(val email: String, val password: String) : LoginUIEvents()
    class ResetPassword(val email: String) : LoginUIEvents()
    object ShowPasswordClick : LoginUIEvents()
    object OpenResetPasswordDialogClicked : LoginUIEvents()
    object DismissResetPasswordDialog : LoginUIEvents()
    object OpenExitAppDialog: LoginUIEvents()
    object DismissExitAppDialog: LoginUIEvents()
}
