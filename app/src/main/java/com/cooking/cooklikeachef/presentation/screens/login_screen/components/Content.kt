package com.cooking.cooklikeachef.presentation.screens.login_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
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
import androidx.navigation.NavController
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.navigation.Screens
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomButton
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomOutlinedTextField
import com.cooking.cooklikeachef.presentation.screens.common_compoments.ExitAppDialog
import com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel.LoginState
import com.cooking.cooklikeachef.presentation.ui.theme.SkyBlue
import kotlinx.coroutines.delay

@Composable
fun Content(
    modifier: Modifier,
    scaffoldState: ScaffoldState,
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
    val localFocus = LocalFocusManager.current

    CustomOutlinedTextField(
        value = state.value.email,
        onValueChange = { email -> eventEmailChanged(email.trim()) },
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
        onValueChange = { password -> eventPasswordChanged(password.trim()) },
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
                        eventShowPassword()
                    }
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "Password Icon",
                    modifier = Modifier.clickable {
                        eventShowPassword()
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
        }),
        isTextVisible = state.value.isPasswordVisible,
        modifier = modifier,
        placeholderSize = textSize,
        isError = !state.value.isPasswordValid,
    )

    if (state.value.errorMessageLoginForm.isNotEmpty()) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.error_message_login),
            color = MaterialTheme.colors.primary
        )
    }

    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(
        modifier = Modifier.width(140.dp),
        enabled = state.value.isEmailValid && state.value.isPasswordValid,
        text = stringResource(id = R.string.login_button_text),
        isLoading = state.value.isLogInLoading
    ) {
        eventSignIn()
    }
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = stringResource(id = R.string.forgot_password_text),
        color = colorResource(id = R.color.general_color),
        fontSize = 12.sp,
        modifier = Modifier.clickable {
            eventOpenResetPasswordDialog()
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

    if (state.value.isResetPasswordSent) {
        LaunchedEffect(Unit) {
            // TODO: works fine but dialog's default background makes it faded
            scaffoldState.snackbarHostState.showSnackbar(
                message = "An email sent to reset your password",
                duration = SnackbarDuration.Short
            )
            delay(1500L)
            eventDismissResetPasswordDialog()
        }
    }

    if (state.value.isResetPasswordDialogOpen) {
        ForgotPasswordDialog(state, eventDialogEmailChanged, eventResetPassword) {
            eventDismissResetPasswordDialog()
        }
    }

    if (state.value.isExitAppDialogOpen) {
        ExitAppDialog(onExitClick = {
            eventExitApp()
        }) {
            eventDismissExitAppDialog()
        }
    }

    if (state.value.isLoggedIn) {
        LaunchedEffect(Unit) {
            navController.navigate(Screens.Home.name) {
                popUpTo(Screens.Login.name) {
                    inclusive = true
                }
            }
        }
    }
}