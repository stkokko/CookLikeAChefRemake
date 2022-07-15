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
    backgroundColor: Color = Color.Transparent,
    iconSize: Dp = 24.dp,
    dropdownMenuWidth: Dp = 146.dp,
    dropdownItemFontSize: TextUnit = 16.sp,
    expandedOptionsMenu: Boolean,
    title: (() -> Unit)? = null,
    openOptionsMenu: () -> Unit,
    closeOptionsMenu: () -> Unit,
    logOut: () -> Unit
) {
    TopAppBar(
        title = { title?.invoke() },
        backgroundColor = backgroundColor,
        contentColor = Color.White,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { openOptionsMenu() }) {
                Icon(Icons.Default.MoreVert, "Option Menu icon", modifier = Modifier.size(iconSize))
            }

            DropdownMenu(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(dropdownMenuWidth),
                expanded = expandedOptionsMenu,
                onDismissRequest = { closeOptionsMenu() }
            ) {

                DropdownMenuItem(onClick = {
                    // TODO: send email action
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
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}