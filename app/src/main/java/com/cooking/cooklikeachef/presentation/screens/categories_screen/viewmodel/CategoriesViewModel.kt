package com.cooking.cooklikeachef.presentation.screens.categories_screen.viewmodel

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.GetRecipes
import com.cooking.cooklikeachef.domain.use_cases.LogOut
import com.cooking.cooklikeachef.presentation.screens.categories_screen.events.CategoriesUIEvents
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getRecipes: GetRecipes,
    private val logOut: LogOut
) : ViewModel() {

    private val _state = mutableStateOf(CategoriesState())
    val state: State<CategoriesState> = _state

    init {
        initRecipes()
    }

    private fun initRecipes() {
        getRecipes().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        recipes = emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        recipes = result.data ?: emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        recipes = emptyList(),
                        errorMessage = result.message ?: "An unexpected error occurred."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: CategoriesUIEvents) {
        when (event) {
            is CategoriesUIEvents.SearchRecipeChanged -> {
                if (event.recipe.isNotEmpty())
                    _state.value = _state.value.copy(
                        searchRecipe = event.recipe,
                        isSearchRecipeDropdownExpanded = true
                    )
                else
                    _state.value = _state.value.copy(
                        searchRecipe = event.recipe,
                        isSearchRecipeDropdownExpanded = false
                    )
            }

            is CategoriesUIEvents.ContactUs -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("cooklikeachef96@gmail.com"))
                    type = "message/rfc822"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                ContextCompat.startActivity(event.context, shareIntent, null)
            }

            is CategoriesUIEvents.SignOff -> {
                handleLogOut()
            }

            is CategoriesUIEvents.DisplayOptionsMenu, CategoriesUIEvents.DismissOptionsMenu -> {
                _state.value =
                    _state.value.copy(isOptionsMenuExpanded = !_state.value.isOptionsMenuExpanded)
            }

            is CategoriesUIEvents.DismissSearchRecipeDropdown -> {
                _state.value =
                    _state.value.copy(isSearchRecipeDropdownExpanded = !_state.value.isSearchRecipeDropdownExpanded)
            }
        }
    }

    private fun handleLogOut() {
        logOut().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value =
                        _state.value.copy(isLoading = true, isLoggedIn = true, errorMessage = "")
                }

                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            isLoggedIn = result.data ?: true,
                            errorMessage = ""
                        )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        errorMessage = result.message ?: "An unexpected error occurred."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}