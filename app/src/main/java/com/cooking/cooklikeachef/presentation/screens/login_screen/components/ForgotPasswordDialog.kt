package com.cooking.cooklikeachef.presentation.screens.login_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomButton
import com.cooking.cooklikeachef.presentation.screens.login_screen.events.LoginUIEvents
import com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel.LoginViewModel
import com.cooking.cooklikeachef.presentation.ui.theme.Cherry
import com.cooking.cooklikeachef.presentation.ui.theme.DarkCherry
import com.cooking.cooklikeachef.presentation.ui.theme.LightCherry

@Composable
fun ForgotPasswordDialog(loginViewModel: LoginViewModel, onDismiss: () -> Unit) {
    val state = loginViewModel.state
    val localFocus = LocalFocusManager.current

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.95f) // TODO when the error message is displayed, it pushed the buttons out of the dialog box
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.reset_password_text),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(id = R.string.reset_password_message),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            TextField(
                value = state.value.dialogEmail,
                onValueChange = { dialogEmail ->
                    loginViewModel.onEvent(LoginUIEvents.DialogEmailChanged(dialogEmail.trim()))
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.e_mail),
                        color = Color.LightGray
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email,
                    autoCorrect = false
                ),
                keyboardActions = KeyboardActions(onDone = {
                    localFocus.clearFocus()
                    loginViewModel.onEvent(LoginUIEvents.ResetPassword(state.value.dialogEmail))
                }),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email, contentDescription = "",
                        tint = LightCherry
                    )
                },
                singleLine = true,
                isError = !state.value.isDialogEmailValid,
                modifier = Modifier.fillMaxWidth(fraction = 0.85f)
            )

            if (state.value.errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = state.value.errorMessage,
                    color = MaterialTheme.colors.primary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButton(
                    modifier = Modifier.width(80.dp),
                    text = stringResource(id = R.string.send_text),
                    enabled = state.value.isDialogEmailValid,
                    isLoading = state.value.isLoading
                ) {
                    loginViewModel.onEvent(LoginUIEvents.ResetPassword(state.value.dialogEmail))
                }
                CustomButton(
                    modifier = Modifier.width(90.dp),
                    text = stringResource(id = R.string.cancel),
                    enabled = true
                ) {
                    loginViewModel.onEvent(LoginUIEvents.ResetDialogEmail)
                    // TODO: does not work cause until we close the dialog the message display on login screen
                    // TODO: but if we don't do this then when we open it again it will display the previous error message
                    // TODO: so we need both resetErrorMessage and the alternate check in login screen when the error message
                    // TODO: is not empty
                    loginViewModel.onEvent(LoginUIEvents.ResetErrorMessage)
                    onDismiss()
                    // TODO: if the dialog close cause we pressed elsewhere on the screen and not on cancel
                    // TODO: then the error message does not reset
                }
            }
        }
    }
}