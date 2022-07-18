package com.cooking.cooklikeachef.presentation.screens.common_compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cooking.cooklikeachef.R

@Composable
fun ExitAppDialog(
    onExitClick: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {

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
                text = stringResource(id = R.string.exit_app_message),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomButton(
                    modifier = Modifier.width(80.dp),
                    text = stringResource(id = R.string.exit),
                    enabled = true
                ) {
                    onExitClick()
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