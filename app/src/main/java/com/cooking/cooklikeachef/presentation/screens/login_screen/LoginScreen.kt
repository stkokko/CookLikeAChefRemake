package com.cooking.cooklikeachef.presentation.screens.login_screen

import android.util.Log
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
import com.cooking.cooklikeachef.presentation.screens.common_compoments.LoginRegisterLayout
import com.cooking.cooklikeachef.presentation.screens.components.ForgotPasswordDialog
import com.cooking.cooklikeachef.presentation.ui.theme.SkyBlue

@Composable
fun LoginScreen(navController: NavController) {
    LoginRegisterLayout(painter = painterResource(id = R.drawable.login_image_header)) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val boxWithConstraintsScope = this
            when {
                boxWithConstraintsScope.maxHeight > 900.dp -> {
                    LoginForm(fraction = 0.7f, navController = navController)
                }
                boxWithConstraintsScope.maxHeight > 600.dp -> {
                    LoginForm(fraction = 0.8f, navController = navController)
                }
                else -> {
                    LoginForm(fraction = 0.75f, textSize = 14.sp, navController = navController)
                }
            }
        }
    }
}

@Composable
private fun LoginForm(fraction: Float, textSize: TextUnit = 16.sp, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginContent(
            modifier = Modifier.fillMaxWidth(fraction = fraction),
            textSize = textSize,
            navController = navController
        )
    }
}

@Composable
private fun LoginContent(
    modifier: Modifier,
    textSize: TextUnit = 16.sp,
    navController: NavController
) {
    var openDialog by remember {
        mutableStateOf(false)
    }
    val localFocus = LocalFocusManager.current
    var email by remember {
        mutableStateOf("")
    }
    var isEmailValid by remember {
        mutableStateOf(true)
    }
    var password by remember {
        mutableStateOf("")
    }
    //TODO
    var isPasswordValid by remember {
        mutableStateOf(true)
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var isConfirmPasswordValid by remember {
        mutableStateOf(true)
    }
    var isConfirmPasswordVisible by remember {
        mutableStateOf(false)
    }

    CustomOutlinedTextField(
        value = email,
        onValueChange = { email = it },
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
        label = "E-Mail",
        trailingIcon = {
            if (!isEmailValid) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "Error Icon"
                )
            }
        },
        modifier = modifier,
        placeholderSize = textSize
    )
    Spacer(modifier = Modifier.height(4.dp))
    CustomOutlinedTextField(
        value = password,
        onValueChange = { password = it },
        placeholder = "Password",
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Password Icon",
                tint = MaterialTheme.colors.primaryVariant
            )
        },
        trailingIcon = {
            if (!isPasswordVisible) {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "Password Icon",
                    modifier = Modifier.clickable {
                        isPasswordVisible = !isPasswordVisible
                    }
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = "Password Icon",
                    modifier = Modifier.clickable {
                        isPasswordVisible = !isPasswordVisible
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
            Log.d("LoginScreen", "LoginScreen: onDoneKB")
            localFocus.clearFocus()
//                                viewModel.login(email, password)
        }),
        isTextVisible = isPasswordVisible,
        modifier = modifier,
        placeholderSize = textSize
    )
    Spacer(modifier = Modifier.height(20.dp))
    CustomButton(
        modifier = Modifier.width(140.dp),
        stringResource(id = R.string.login_button_text)
    ) {
        //TODO
    }
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = stringResource(id = R.string.forgot_password_text),
        color = colorResource(id = R.color.general_color),
        fontSize = 12.sp,
        modifier = Modifier.clickable {
            openDialog = true
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
    if (openDialog) {
        ForgotPasswordDialog {
            openDialog = false
        }
    }
}