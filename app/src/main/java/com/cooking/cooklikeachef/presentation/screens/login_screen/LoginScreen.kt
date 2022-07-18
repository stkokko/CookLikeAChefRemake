package com.cooking.cooklikeachef.presentation.screens.login_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.screens.common_compoments.ExitAppDialog
import com.cooking.cooklikeachef.presentation.screens.common_compoments.LoginRegisterLayout
import com.cooking.cooklikeachef.presentation.screens.login_screen.components.Content
import com.cooking.cooklikeachef.presentation.screens.login_screen.events.LoginUIEvents
import com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel.LoginState
import com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    LoginRegisterLayout(painter = painterResource(id = R.drawable.login_image_header)) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val boxWithConstraintsScope = this
            when {
                boxWithConstraintsScope.maxHeight > 900.dp -> {
                    LoginForm(
                        fraction = 0.7f,
                        navController = navController,
                        state = loginViewModel.state,
                        eventEmailChanged = { email ->
                            loginViewModel.onEvent(LoginUIEvents.EmailChanged(email))
                        },
                        eventPasswordChanged = { password ->
                            loginViewModel.onEvent(LoginUIEvents.PasswordChanged(password))
                        },
                        eventShowPassword = {
                            loginViewModel.onEvent(LoginUIEvents.ShowPasswordClick)
                        },
                        eventSignIn = {
                            loginViewModel.onEvent(
                                LoginUIEvents.SignIn(
                                    loginViewModel.state.value.email,
                                    loginViewModel.state.value.password
                                )
                            )
                        },
                        eventOpenDialog = {
                            loginViewModel.onEvent(LoginUIEvents.OpenResetPasswordDialogClicked)
                        },
                        eventDismissDialog = {
                            loginViewModel.onEvent(LoginUIEvents.DismissResetPasswordDialog)
                        },
                        eventDialogEmailChanged = { dialogEmail ->
                            loginViewModel.onEvent(LoginUIEvents.DialogEmailChanged(dialogEmail))
                        },
                        eventResetPassword = {
                            loginViewModel.onEvent(LoginUIEvents.ResetPassword(loginViewModel.state.value.dialogEmail))
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    LoginForm(
                        fraction = 0.8f,
                        navController = navController,
                        state = loginViewModel.state,
                        eventEmailChanged = { email ->
                            loginViewModel.onEvent(LoginUIEvents.EmailChanged(email))
                        },
                        eventPasswordChanged = { password ->
                            loginViewModel.onEvent(LoginUIEvents.PasswordChanged(password))
                        },
                        eventShowPassword = {
                            loginViewModel.onEvent(LoginUIEvents.ShowPasswordClick)
                        },
                        eventSignIn = {
                            loginViewModel.onEvent(
                                LoginUIEvents.SignIn(
                                    loginViewModel.state.value.email,
                                    loginViewModel.state.value.password
                                )
                            )
                        },
                        eventOpenDialog = {
                            loginViewModel.onEvent(LoginUIEvents.OpenResetPasswordDialogClicked)
                        },
                        eventDismissDialog = {
                            loginViewModel.onEvent(LoginUIEvents.DismissResetPasswordDialog)
                        },
                        eventDialogEmailChanged = { dialogEmail ->
                            loginViewModel.onEvent(LoginUIEvents.DialogEmailChanged(dialogEmail))
                        },
                        eventResetPassword = {
                            loginViewModel.onEvent(LoginUIEvents.ResetPassword(loginViewModel.state.value.dialogEmail))
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 620.dp -> {
                    LoginForm(
                        fraction = 0.8f,
                        navController = navController,
                        state = loginViewModel.state,
                        eventEmailChanged = { email ->
                            loginViewModel.onEvent(LoginUIEvents.EmailChanged(email))
                        },
                        eventPasswordChanged = { password ->
                            loginViewModel.onEvent(LoginUIEvents.PasswordChanged(password))
                        },
                        eventShowPassword = {
                            loginViewModel.onEvent(LoginUIEvents.ShowPasswordClick)
                        },
                        eventSignIn = {
                            loginViewModel.onEvent(
                                LoginUIEvents.SignIn(
                                    loginViewModel.state.value.email,
                                    loginViewModel.state.value.password
                                )
                            )
                        },
                        eventOpenDialog = {
                            loginViewModel.onEvent(LoginUIEvents.OpenResetPasswordDialogClicked)
                        },
                        eventDismissDialog = {
                            loginViewModel.onEvent(LoginUIEvents.DismissResetPasswordDialog)
                        },
                        eventDialogEmailChanged = { dialogEmail ->
                            loginViewModel.onEvent(LoginUIEvents.DialogEmailChanged(dialogEmail))
                        },
                        eventResetPassword = {
                            loginViewModel.onEvent(LoginUIEvents.ResetPassword(loginViewModel.state.value.dialogEmail))
                        }
                    )
                }
                else -> {
                    LoginForm(
                        fraction = 0.76f,
                        textSize = 14.sp,
                        navController = navController,
                        state = loginViewModel.state,
                        eventEmailChanged = { email ->
                            loginViewModel.onEvent(LoginUIEvents.EmailChanged(email))
                        },
                        eventPasswordChanged = { password ->
                            loginViewModel.onEvent(LoginUIEvents.PasswordChanged(password))
                        },
                        eventShowPassword = {
                            loginViewModel.onEvent(LoginUIEvents.ShowPasswordClick)
                        },
                        eventSignIn = {
                            loginViewModel.onEvent(
                                LoginUIEvents.SignIn(
                                    loginViewModel.state.value.email,
                                    loginViewModel.state.value.password
                                )
                            )
                        },
                        eventOpenDialog = {
                            loginViewModel.onEvent(LoginUIEvents.OpenResetPasswordDialogClicked)
                        },
                        eventDismissDialog = {
                            loginViewModel.onEvent(LoginUIEvents.DismissResetPasswordDialog)
                        },
                        eventDialogEmailChanged = { dialogEmail ->
                            loginViewModel.onEvent(LoginUIEvents.DialogEmailChanged(dialogEmail))
                        },
                        eventResetPassword = {
                            loginViewModel.onEvent(LoginUIEvents.ResetPassword(loginViewModel.state.value.dialogEmail))
                        }
                    )
                }
            }
        }
    }

    BackHandler {
        // TODO: Should change the state and show the dialog
//        ExitAppDialog(onExitClick = {
//            loginViewModel.onEvent(LoginUIEvents.ExitAppClicked)
//        }
//        ) {
//            loginViewModel.onEvent(LoginUIEvents.DismissExitAppDialog)
//        }

    }
}

@Composable
private fun LoginForm(
    fraction: Float,
    textSize: TextUnit = 16.sp,
    navController: NavController,
    state: State<LoginState>,
    eventEmailChanged: (String) -> Unit,
    eventPasswordChanged: (String) -> Unit,
    eventShowPassword: () -> Unit,
    eventSignIn: () -> Unit,
    eventOpenDialog: () -> Unit,
    eventDismissDialog: () -> Unit,
    eventDialogEmailChanged: (String) -> Unit,
    eventResetPassword: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Content(
            modifier = Modifier.fillMaxWidth(fraction = fraction),
            textSize = textSize,
            navController = navController,
            state = state,
            eventEmailChanged = eventEmailChanged,
            eventPasswordChanged = eventPasswordChanged,
            eventShowPassword = eventShowPassword,
            eventSignIn = eventSignIn,
            eventOpenDialog = eventOpenDialog,
            eventDismissDialog = eventDismissDialog,
            eventDialogEmailChanged = eventDialogEmailChanged,
            eventResetPassword = eventResetPassword
        )
    }
}