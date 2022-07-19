package com.cooking.cooklikeachef.presentation.screens.common_compoments

import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cooking.cooklikeachef.R
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.cooking.cooklikeachef.domain.model.Recipe

@Composable
fun RecipeResultDropdown(
    expandedDropdown: Boolean,
    recipes: List<Recipe>,
    onDismissDropdown: () -> Unit
) {
    // TODO: why there is space top and bottom of the dropdown...tried padding vertical 0.dp
    DropdownMenu(
        expanded = true, // TODO
        onDismissRequest = { onDismissDropdown() },
        properties = PopupProperties(focusable = false),
        modifier = Modifier
            .width(270.dp)
            .wrapContentHeight()
            .heightIn(max = 300.dp)
    ) {
        if (recipes.isNotEmpty()) {
            recipes.forEach { recipe ->
                DropdownMenuItem(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 2.dp)
                ) {
                    AsyncImage(
                        model = recipe.imageURL,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(fraction = 0.3f)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = recipe.name,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        } else {
            Text(
                text = stringResource(id = R.string.list_is_empty),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}