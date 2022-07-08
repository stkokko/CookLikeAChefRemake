package com.cooking.cooklikeachef.presentation.screens.login_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cooking.cooklikeachef.R
import com.cooking.cooklikeachef.presentation.screens.common_compoments.CustomButton
import com.cooking.cooklikeachef.presentation.ui.theme.DarkCherry

// TODO: components of login_screen

@Composable
fun ForgotPasswordDialog(onDismiss: () -> Unit) {
    var email by remember {
        mutableStateOf("")
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.95f)
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
            TextField(value = email, onValueChange = {
                email = it
            }, placeholder = {
                Text(
                    text = stringResource(id = R.string.e_mail),
                    color = Color.LightGray
                )
            }, colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White
            ), leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, contentDescription = "",
                    tint = DarkCherry
                )
            }, singleLine = true, modifier = Modifier.fillMaxWidth(fraction = 0.85f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButton(
                    modifier = Modifier.width(80.dp),
                    text = stringResource(id = R.string.send_text)
                ) {
                    //TODO
                }
                CustomButton(
                    modifier = Modifier.width(90.dp),
                    text = stringResource(id = R.string.cancel)
                ) {
                    onDismiss()
                }
            }
        }
    }
}