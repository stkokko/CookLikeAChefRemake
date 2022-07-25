package com.cooking.cooklikeachef.presentation.screens.login_screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
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
    val activity = LocalContext.current as Activity
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        LoginRegisterLayout(painter = painterResource(id = R.drawable.login_image_header)) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val boxWithConstraintsScope = this
                when {
                    boxWithConstraintsScope.maxHeight > 900.dp -> {
                        LoginForm(
                            scaffoldState = scaffoldState,
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
                            eventOpenResetPasswordDialog = {
                                loginViewModel.onEvent(LoginUIEvents.OpenResetPasswordDialogClicked)
                            },
                            eventDismissResetPasswordDialog = {
                                loginViewModel.onEvent(LoginUIEvents.DismissResetPasswordDialog)
                            },
                            eventDialogEmailChanged = { dialogEmail ->
                                loginViewModel.onEvent(LoginUIEvents.DialogEmailChanged(dialogEmail))
                            },
                            eventResetPassword = {
                                loginViewModel.onEvent(LoginUIEvents.ResetPassword(loginViewModel.state.value.dialogEmail))
                            },
                            eventExitApp = {
                                activity.finish()
                            },
                            eventDismissExitAppDialog = {
                                loginViewModel.onEvent(LoginUIEvents.DismissExitAppDialog)
                            }
                        )
                    }
                    boxWithConstraintsScope.maxHeight > 780.dp -> {
                        LoginForm(
                            scaffoldState = scaffoldState,
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
                            eventOpenResetPasswordDialog = {
                                loginViewModel.onEvent(LoginUIEvents.OpenResetPasswordDialogClicked)
                            },
                            eventDismissResetPasswordDialog = {
                                loginViewModel.onEvent(LoginUIEvents.DismissResetPasswordDialog)
                            },
                            eventDialogEmailChanged = { dialogEmail ->
                                loginViewModel.onEvent(LoginUIEvents.DialogEmailChanged(dialogEmail))
                            },
                            eventResetPassword = {
                                loginViewModel.onEvent(LoginUIEvents.ResetPassword(loginViewModel.state.value.dialogEmail))
                            },
                            eventExitApp = {
                                activity.finish()
                            },
                            eventDismissExitAppDialog = {
                                loginViewModel.onEvent(LoginUIEvents.DismissExitAppDialog)
                            }
                        )
                    }
                    boxWithConstraintsScope.maxHeight > 620.dp -> {
                        LoginForm(
                            scaffoldState = scaffoldState,
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
                            eventOpenResetPasswordDialog = {
                                loginViewModel.onEvent(LoginUIEvents.OpenResetPasswordDialogClicked)
                            },
                            eventDismissResetPasswordDialog = {
                                loginViewModel.onEvent(LoginUIEvents.DismissResetPasswordDialog)
                            },
                            eventDialogEmailChanged = { dialogEmail ->
                                loginViewModel.onEvent(LoginUIEvents.DialogEmailChanged(dialogEmail))
                            },
                            eventResetPassword = {
                                loginViewModel.onEvent(LoginUIEvents.ResetPassword(loginViewModel.state.value.dialogEmail))
                            },
                            eventExitApp = {
                                activity.finish()
                            },
                            eventDismissExitAppDialog = {
                                loginViewModel.onEvent(LoginUIEvents.DismissExitAppDialog)
                            }
                        )
                    }
                    else -> {
                        LoginForm(
                            scaffoldState = scaffoldState,
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
                            eventOpenResetPasswordDialog = {
                                loginViewModel.onEvent(LoginUIEvents.OpenResetPasswordDialogClicked)
                            },
                            eventDismissResetPasswordDialog = {
                                loginViewModel.onEvent(LoginUIEvents.DismissResetPasswordDialog)
                            },
                            eventDialogEmailChanged = { dialogEmail ->
                                loginViewModel.onEvent(LoginUIEvents.DialogEmailChanged(dialogEmail))
                            },
                            eventResetPassword = {
                                loginViewModel.onEvent(LoginUIEvents.ResetPassword(loginViewModel.state.value.dialogEmail))
                            },
                            eventExitApp = {
                                activity.finish()
                            },
                            eventDismissExitAppDialog = {
                                loginViewModel.onEvent(LoginUIEvents.DismissExitAppDialog)
                            }
                        )
                    }
                }
            }
        }
    }

    BackHandler {
        loginViewModel.onEvent(LoginUIEvents.OpenExitAppDialog)
    }
}

@Composable
private fun LoginForm(
    scaffoldState: ScaffoldState,
    fraction: Float,
    textSize: TextUnit = 16.sp,
    navController: NavController,
    state: State<LoginState>,
    eventEmailChanged: (String) -> Unit,
    eventPasswordChanged: (String) -> Unit,
    eventShowPassword: () -> Unit,
    eventSignIn: () -> Unit,
    eventOpenResetPasswordDialog: () -> Unit,
    eventDismissResetPasswordDialog: () -> Unit,
    eventDialogEmailChanged: (String) -> Unit,
    eventResetPassword: () -> Unit,
    eventExitApp: () -> Unit,
    eventDismissExitAppDialog: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Content(
            modifier = Modifier.fillMaxWidth(fraction = fraction),
            scaffoldState = scaffoldState,
            textSize = textSize,
            navController = navController,
            state = state,
            eventEmailChanged = eventEmailChanged,
            eventPasswordChanged = eventPasswordChanged,
            eventShowPassword = eventShowPassword,
            eventSignIn = eventSignIn,
            eventOpenResetPasswordDialog = eventOpenResetPasswordDialog,
            eventDismissResetPasswordDialog = eventDismissResetPasswordDialog,
            eventDialogEmailChanged = eventDialogEmailChanged,
            eventResetPassword = eventResetPassword,
            eventExitApp = eventExitApp,
            eventDismissExitAppDialog = eventDismissExitAppDialog
        )
    }
}