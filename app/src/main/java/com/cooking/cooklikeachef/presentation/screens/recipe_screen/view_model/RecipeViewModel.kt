package com.cooking.cooklikeachef.presentation.screens.recipe_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.use_cases.GetRecipe
import com.cooking.cooklikeachef.domain.use_cases.UpdateFavouriteRecipe
import com.cooking.cooklikeachef.presentation.screens.recipe_screen.events.RecipeUIEvents
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val updateFavouriteRecipe: UpdateFavouriteRecipe,
    private val getRecipe: GetRecipe
) : ViewModel() {
    private val _state = mutableStateOf(RecipeState())
    val state: State<RecipeState> = _state

    fun onEvent(event: RecipeUIEvents) {
        when (event) {

            is RecipeUIEvents.GetRecipeById -> {
                handleGetRecipeById(event.recipeId)
            }

            is RecipeUIEvents.UpdateFavourites -> {
                handleUpdateFavourites(event.recipe)
            }
        }
    }

    private fun handleGetRecipeById(recipeId: String) {
        getRecipe(recipeId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value =
                        _state.value.copy(isLoading = true, recipe = null, errorMessage = "")
                }

                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            recipe = result.data,
                            errorMessage = ""
                        )
                }

                is Resource.Error -> {
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            recipe = null,
                            errorMessage = "An unexpected error occurred."
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun handleUpdateFavourites(recipe: Recipe) {

    }
}