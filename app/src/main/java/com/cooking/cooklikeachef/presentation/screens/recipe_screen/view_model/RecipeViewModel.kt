package com.cooking.cooklikeachef.presentation.screens.recipe_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.use_cases.UpdateFavouriteRecipe
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.events.RecipeUIEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val updateFavouriteRecipe: UpdateFavouriteRecipe
) : ViewModel() {
    private val _state = mutableStateOf(RecipeState())
    val state: State<RecipeState> = _state

    fun onEvent(event: RecipeUIEvents) {
        when (event) {
            is RecipeUIEvents.UpdateFavourites -> {
                handleUpdateFavourites(event.recipe)
            }
        }
    }

    private fun handleUpdateFavourites(recipe: Recipe) {
        // TODO
    }
}