package com.cooking.cooklikeachef.presentation.screens.register_screen.components

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
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
import com.cooking.cooklikeachef.presentation.screens.register_screen.viewmodel.RegisterState
import com.cooking.cooklikeachef.presentation.ui.theme.SkyBlue

@Composable
fun Content(
    modifier: Modifier,
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
        onValueChange = { password ->
            eventPasswordChanged(password.trim())
        },
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
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password,
            autoCorrect = false
        ),
        label = "Password",
        keyboardActions = KeyboardActions(
            onNext = {
                localFocus.moveFocus(FocusDirection.Down)
            }),
        isTextVisible = state.value.isPasswordVisible,
        modifier = modifier,
        placeholderSize = textSize,
        isError = !state.value.isPasswordValid
    )
    Spacer(modifier = Modifier.height(4.dp))
    CustomOutlinedTextField(
        value = state.value.confirmPassword,
        onValueChange = { confirmPassword ->
            eventConfirmPasswordChanged(
                confirmPassword.trim()
            )
        },
        placeholder = "Confirm Password",
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Confirm Password Icon",
                tint = MaterialTheme.colors.primaryVariant
            )
        },
        trailingIcon = {
            if (!state.value.isConfirmPasswordVisible) {
                Icon(
                    imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = "Confirm Password Icon",
                    modifier = Modifier.clickable {
                        eventShowConfirmPassword()
                    }
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "Confirm Password Icon",
                    modifier = Modifier.clickable {
                        eventShowConfirmPassword()
                    }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            autoCorrect = false
        ),
        label = "Confirm Password",
        keyboardActions = KeyboardActions(onDone = {
            localFocus.clearFocus()
            eventCreateUser()
        }),
        isTextVisible = state.value.isConfirmPasswordVisible,
        modifier = modifier,
        placeholderSize = textSize,
        isError = !state.value.isConfirmPasswordValid
    )

    if (state.value.errorMessage.isNotEmpty()) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = state.value.errorMessage, color = MaterialTheme.colors.primary)
    }

    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(
        modifier = Modifier.width(140.dp),
        enabled = state.value.isEmailValid && state.value.isPasswordValid && state.value.isConfirmPasswordValid,
        text = stringResource(id = R.string.register),
        isLoading = state.value.isLoading
    ) {
        eventCreateUser()
    }
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.register_textView),
            fontSize = textSize
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.login_button_text),
            color = SkyBlue,
            fontSize = textSize,
            modifier = Modifier.clickable {
                navController.navigate(Screens.Login.name) {
                    popUpTo(Screens.Register.name) {
                        inclusive = true
                    }
                }
            })
    }

    if (state.value.isExitAppDialogOpen) {
        ExitAppDialog(onExitClick = {
            eventExitApp()
        }) {
            eventDismissExitAppDialog()
        }
    }

    if (state.value.isRegistered) {
        LaunchedEffect(Unit) {
            navController.navigate(Screens.Home.name) {
                popUpTo(Screens.Register.name) {
                    inclusive = true
                }
            }
        }
    }
}