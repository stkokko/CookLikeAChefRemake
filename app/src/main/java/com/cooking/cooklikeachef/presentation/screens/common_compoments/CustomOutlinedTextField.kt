package com.cooking.cooklikeachef.presentation.screens.common_compoments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


@Composable
fun CustomOutlinedTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String = "",
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    label: String = "",
    modifier: Modifier = Modifier,
    isTextVisible: Boolean = true,
    placeholderSize: TextUnit
) {


    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        maxLines = 1,
        singleLine = true,
        placeholder = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = placeholder,
                    fontSize = placeholderSize,
                    color = Color.LightGray.copy(alpha = 0.6f)
                )
            }
        }, shape = RoundedCornerShape(CornerSize(30.dp)),
        leadingIcon = {
            leadingIcon()
        },
        keyboardOptions = keyboardOptions,
        label = {
            Text(
                text = label,
                fontSize = placeholderSize
            )
        },
        trailingIcon = { trailingIcon() },
        visualTransformation = if (isTextVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardActions = keyboardActions
    )

}