package com.cooking.cooklikeachef.presentation.screens.common_compoments

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cooking.cooklikeachef.R

@Composable
fun OptionsMenu(
    iconSize: Dp = 24.dp,
    dropdownMenuWidth: Dp = 150.dp,
    dropdownItemFontSize: TextUnit = 16.sp,
    isOptionsMenuExpanded: Boolean,
    openOptionsMenu: () -> Unit,
    dismissOptionsMenu: () -> Unit,
    contactUs: () -> Unit,
    logOut: () -> Unit
) {
    IconButton(onClick = { openOptionsMenu() }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            tint = Color.White,
            contentDescription = "Option Menu icon",
            modifier = Modifier.size(iconSize)
        )
    }
    DropdownMenu(
        modifier = Modifier
            .wrapContentHeight()
            .width(dropdownMenuWidth),
        expanded = isOptionsMenuExpanded,
        onDismissRequest = { dismissOptionsMenu() }
    ) {

        DropdownMenuItem(onClick = {
            contactUs()
        }) {
            Icon(imageVector = Icons.Default.Email, contentDescription = "Contact Us icon")
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.contact_us),
                fontSize = dropdownItemFontSize
            )
        }

        DropdownMenuItem(onClick = {
            logOut()
        }) {
            Icon(imageVector = Icons.Default.Logout, contentDescription = "Log Out icon")
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.logout),
                fontSize = dropdownItemFontSize
            )
        }
    }
}