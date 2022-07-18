package com.cooking.cooklikeachef.presentation.screens.register_screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
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
import com.cooking.cooklikeachef.presentation.screens.register_screen.components.Content
import com.cooking.cooklikeachef.presentation.screens.register_screen.events.RegisterUIEvents
import com.cooking.cooklikeachef.presentation.screens.register_screen.viewmodel.RegisterState
import com.cooking.cooklikeachef.presentation.screens.register_screen.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity

    LoginRegisterLayout(painter = painterResource(id = R.drawable.register_background_image)) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val boxWithConstraintsScope = this
            when {
                boxWithConstraintsScope.maxHeight > 900.dp -> {
                    RegisterForm(
                        fraction = 0.7f,
                        navController = navController,
                        state = registerViewModel.state,
                        eventEmailChanged = { email ->
                            registerViewModel.onEvent(
                                RegisterUIEvents.EmailChanged(email)
                            )
                        },
                        eventPasswordChanged = { password ->
                            registerViewModel.onEvent(RegisterUIEvents.PasswordChanged(password))
                        },
                        eventConfirmPasswordChanged = { confirmPassword ->
                            registerViewModel.onEvent(
                                RegisterUIEvents.ConfirmPasswordChanged(
                                    registerViewModel.state.value.password,
                                    confirmPassword
                                )
                            )
                        },
                        eventShowPassword = {
                            registerViewModel.onEvent(RegisterUIEvents.ShowPasswordClick)
                        },
                        eventShowConfirmPassword = {
                            registerViewModel.onEvent(RegisterUIEvents.ShowConfirmPasswordClick)
                        },
                        eventCreateUser = {
                            registerViewModel.onEvent(
                                RegisterUIEvents.CreateUser(
                                    registerViewModel.state.value.email,
                                    registerViewModel.state.value.password
                                )
                            )
                        },
                        eventExitApp = {
                            activity.finish()
                        },
                        eventDismissExitAppDialog = {
                            registerViewModel.onEvent(RegisterUIEvents.DismissExitAppDialog)
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    RegisterForm(
                        fraction = 0.7f,
                        navController = navController,
                        state = registerViewModel.state,
                        eventEmailChanged = { email ->
                            registerViewModel.onEvent(
                                RegisterUIEvents.EmailChanged(email)
                            )
                        },
                        eventPasswordChanged = { password ->
                            registerViewModel.onEvent(RegisterUIEvents.PasswordChanged(password))
                        },
                        eventConfirmPasswordChanged = { confirmPassword ->
                            registerViewModel.onEvent(
                                RegisterUIEvents.ConfirmPasswordChanged(
                                    registerViewModel.state.value.password,
                                    confirmPassword
                                )
                            )
                        },
                        eventShowPassword = {
                            registerViewModel.onEvent(RegisterUIEvents.ShowPasswordClick)
                        },
                        eventShowConfirmPassword = {
                            registerViewModel.onEvent(RegisterUIEvents.ShowConfirmPasswordClick)
                        },
                        eventCreateUser = {
                            registerViewModel.onEvent(
                                RegisterUIEvents.CreateUser(
                                    registerViewModel.state.value.email,
                                    registerViewModel.state.value.password
                                )
                            )
                        },
                        eventExitApp = {
                            activity.finish()
                        },
                        eventDismissExitAppDialog = {
                            registerViewModel.onEvent(RegisterUIEvents.DismissExitAppDialog)
                        }
                    )
                }
                boxWithConstraintsScope.maxHeight > 620.dp -> {
                    RegisterForm(
                        0.8f,
                        navController = navController,
                        state = registerViewModel.state,
                        eventEmailChanged = { email ->
                            registerViewModel.onEvent(
                                RegisterUIEvents.EmailChanged(email)
                            )
                        },
                        eventPasswordChanged = { password ->
                            registerViewModel.onEvent(RegisterUIEvents.PasswordChanged(password))
                        },
                        eventConfirmPasswordChanged = { confirmPassword ->
                            registerViewModel.onEvent(
                                RegisterUIEvents.ConfirmPasswordChanged(
                                    registerViewModel.state.value.password,
                                    confirmPassword
                                )
                            )
                        },
                        eventShowPassword = {
                            registerViewModel.onEvent(RegisterUIEvents.ShowPasswordClick)
                        },
                        eventShowConfirmPassword = {
                            registerViewModel.onEvent(RegisterUIEvents.ShowConfirmPasswordClick)
                        },
                        eventCreateUser = {
                            registerViewModel.onEvent(
                                RegisterUIEvents.CreateUser(
                                    registerViewModel.state.value.email,
                                    registerViewModel.state.value.password
                                )
                            )
                        },
                        eventExitApp = {
                            activity.finish()
                        },
                        eventDismissExitAppDialog = {
                            registerViewModel.onEvent(RegisterUIEvents.DismissExitAppDialog)
                        }
                    )
                }
                else -> {
                    RegisterForm(
                        fraction = 0.75f,
                        textSize = 14.sp,
                        navController = navController,
                        state = registerViewModel.state,
                        eventEmailChanged = { email ->
                            registerViewModel.onEvent(
                                RegisterUIEvents.EmailChanged(email)
                            )
                        },
                        eventPasswordChanged = { password ->
                            registerViewModel.onEvent(RegisterUIEvents.PasswordChanged(password))
                        },
                        eventConfirmPasswordChanged = { confirmPassword ->
                            registerViewModel.onEvent(
                                RegisterUIEvents.ConfirmPasswordChanged(
                                    registerViewModel.state.value.password,
                                    confirmPassword
                                )
                            )
                        },
                        eventShowPassword = {
                            registerViewModel.onEvent(RegisterUIEvents.ShowPasswordClick)
                        },
                        eventShowConfirmPassword = {
                            registerViewModel.onEvent(RegisterUIEvents.ShowConfirmPasswordClick)
                        },
                        eventCreateUser = {
                            registerViewModel.onEvent(
                                RegisterUIEvents.CreateUser(
                                    registerViewModel.state.value.email,
                                    registerViewModel.state.value.password
                                )
                            )
                        },
                        eventExitApp = {
                            activity.finish()
                        },
                        eventDismissExitAppDialog = {
                            registerViewModel.onEvent(RegisterUIEvents.DismissExitAppDialog)
                        }
                    )
                }
            }
        }
    }

    BackHandler {
        registerViewModel.onEvent(RegisterUIEvents.OpenExitAppDialog)
    }
}

@Composable
private fun RegisterForm(
    fraction: Float,
    textSize: TextUnit = 16.sp,
    navController: NavController,
    state: State<RegisterState>,
    eventEmailChanged: (String) -> Unit,
    eventPasswordChanged: (String) -> Unit,
    eventConfirmPasswordChanged: (String) -> Unit,
    eventShowPassword: () -> Unit,
    eventShowConfirmPassword: () -> Unit,
    eventCreateUser: () -> Unit,
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
            textSize = textSize,
            navController = navController,
            state = state,
            eventEmailChanged = eventEmailChanged,
            eventPasswordChanged = eventPasswordChanged,
            eventConfirmPasswordChanged = eventConfirmPasswordChanged,
            eventShowPassword = eventShowPassword,
            eventShowConfirmPassword = eventShowConfirmPassword,
            eventCreateUser = eventCreateUser,
            eventExitApp = eventExitApp,
            eventDismissExitAppDialog = eventDismissExitAppDialog
        )
    }
}