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
import com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel.LoginState
import com.cooking.cooklikeachef.presentation.ui.theme.LightCherry

@Composable
fun ForgotPasswordDialog(
    state: State<LoginState>,
    eventDialogEmailChanged: (String) -> Unit,
    eventResetPassword: () -> Unit,
    onDismiss: () -> Unit
) {
    val localFocus = LocalFocusManager.current
    val height = if (state.value.errorMessageDialog.isNotEmpty()) 210.dp else 190.dp

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.95f)
                .height(height)// TODO when the error message is displayed, it pushes the buttons out of the dialog box
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
                    eventDialogEmailChanged(dialogEmail.trim())
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
                    eventResetPassword()
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

            if (state.value.errorMessageDialog.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = state.value.errorMessageDialog,
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
                    isLoading = state.value.isResetPasswordLoading
                ) {
                    eventResetPassword()
                }
                CustomButton(
                    modifier = Modifier.width(90.dp),
                    text = stringResource(id = R.string.cancel),
                    enabled = true
                ) {
                    onDismiss()
                }
            }
        }
    }
}