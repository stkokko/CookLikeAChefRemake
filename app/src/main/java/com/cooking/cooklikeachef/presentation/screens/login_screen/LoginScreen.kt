package com.cooking.cooklikeachef.presentation.screens.login_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomButton
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomOutlinedTextField
import com.cooking.cooklikeachef.presentation.screens.common_compoments.LoginRegisterLayout
import com.cooking.cooklikeachef.presentation.screens.login_screen.components.ForgotPasswordDialog
import com.cooking.cooklikeachef.presentation.screens.login_screen.events.LoginUIEvents
import com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel.LoginViewModel
import com.cooking.cooklikeachef.presentation.ui.theme.SkyBlue

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
                        loginViewModel = loginViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 780.dp -> {
                    LoginForm(
                        fraction = 0.8f,
                        navController = navController,
                        loginViewModel = loginViewModel
                    )
                }

                boxWithConstraintsScope.maxHeight > 600.dp -> {
                    LoginForm(
                        fraction = 0.8f,
                        navController = navController,
                        loginViewModel = loginViewModel
                    )
                }
                else -> {
                    LoginForm(
                        fraction = 0.76f,
                        textSize = 14.sp,
                        navController = navController,
                        loginViewModel = loginViewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun LoginForm(
    fraction: Float,
    textSize: TextUnit = 16.sp,
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginContent(
            modifier = Modifier.fillMaxWidth(fraction = fraction),
            textSize = textSize,
            navController = navController,
            loginViewModel = loginViewModel
        )
    }
}

@Composable
private fun LoginContent(
    modifier: Modifier,
    textSize: TextUnit = 16.sp,
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val localFocus = LocalFocusManager.current
    val state = loginViewModel.state

    CustomOutlinedTextField(
        value = state.value.email,
        onValueChange = { email -> loginViewModel.onEvent(LoginUIEvents.EmailChanged(email.trim())) },
        placeholder = "E-Mail",
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email Icons",
                tint = MaterialTheme.colors.primaryVariant
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
            autoCorrect = false
        ),
        keyboardActions = KeyboardActions(
            onNext = { localFocus.moveFocus(FocusDirection.Down) }
        ),
        label = "E-Mail",
        trailingIcon = {
            if (!state.value.isEmailValid && state.value.email.isNotEmpty()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "Error Icon"
                )
            }
        },
        modifier = modifier,
        placeholderSize = textSize,
        isError = !state.value.isEmailValid
    )
    Spacer(modifier = Modifier.height(4.dp))
    CustomOutlinedTextField(
        value = state.value.password,
        onValueChange = { password -> loginViewModel.onEvent(LoginUIEvents.PasswordChanged(password.trim())) },
        placeholder = "Password",
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Password Icon",
                tint = MaterialTheme.colors.primaryVariant
            )
        },
        trailingIcon = {
            if (!state.value.isPasswordVisible) {
                Icon(
                    imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = "Password Icon",
                    modifier = Modifier.clickable {
                        loginViewModel.onEvent(LoginUIEvents.ShowPasswordClick)
                    }
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "Password Icon",
                    modifier = Modifier.clickable {
                        loginViewModel.onEvent(LoginUIEvents.ShowPasswordClick)
                    }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            autoCorrect = false
        ),
        label = "Password",
        keyboardActions = KeyboardActions(onDone = {
            localFocus.clearFocus()
            loginViewModel.onEvent(LoginUIEvents.SignIn(state.value.email, state.value.password))
        }),
        isTextVisible = state.value.isPasswordVisible,
        modifier = modifier,
        placeholderSize = textSize,
        isError = !state.value.isPasswordValid,
    )

    if(state.value.errorMessage.isNotEmpty()) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(id = R.string.error_message_login), color = MaterialTheme.colors.primary)
    }

    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(
        modifier = Modifier.width(140.dp),
        enabled = state.value.isEmailValid && state.value.isPasswordValid,
        text = stringResource(id = R.string.login_button_text),
        isLoading = state.value.isLoading
    ) {
        loginViewModel.onEvent(LoginUIEvents.SignIn(state.value.email, state.value.password))
    }
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = stringResource(id = R.string.forgot_password_text),
        color = colorResource(id = R.color.general_color),
        fontSize = 12.sp,
        modifier = Modifier.clickable {
            loginViewModel.onEvent(LoginUIEvents.OpenDialogClicked)
        }
    )
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.new_to_appname),
            fontSize = textSize
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.register),
            color = SkyBlue,
            fontSize = textSize,
            modifier = Modifier.clickable {
                navController.navigate(Screens.Register.name) {
                    popUpTo(Screens.Login.name) {
                        inclusive = true
                    }
                }
            })
    }
    if (state.value.openDialog) {
        ForgotPasswordDialog {
            loginViewModel.onEvent(LoginUIEvents.DialogDismissed)
        }
    }

    if (state.value.isLoggedIn) {
        LaunchedEffect(Unit) {
            navController.navigate(Screens.Main.name) {
                popUpTo(Screens.Login.name) {
                    inclusive = true
                }
            }
        }
    }
}