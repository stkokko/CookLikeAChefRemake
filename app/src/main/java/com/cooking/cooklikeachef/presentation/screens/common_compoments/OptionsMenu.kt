package com.cooking.cooklikeachef.presentation.screens.common_compoments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cooking.cooklikeachef.R

@Composable
fun OptionsMenu(
    backgroundColor: Color = Color.Transparent,
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
                Icon(Icons.Default.MoreVert, "")
            }

            DropdownMenu(
                expanded = expandedOptionsMenu,
                onDismissRequest = { closeOptionsMenu() }
            ) {

                DropdownMenuItem(onClick = {
                    // TODO: send email action
                }) {
                    Text(text = stringResource(id = R.string.contact_us))
                }

                DropdownMenuItem(onClick = {
                    logOut()
                }) {
                    Text(text = stringResource(id = R.string.logout))
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}