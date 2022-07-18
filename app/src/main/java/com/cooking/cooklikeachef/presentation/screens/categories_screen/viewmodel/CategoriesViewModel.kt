package com.cooking.cooklikeachef.presentation.screens.categories_screen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.LogOut
import com.cooking.cooklikeachef.presentation.screens.categories_screen.events.CategoriesUIEvents
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val logOut: LogOut
) : ViewModel() {

    private val _state = mutableStateOf(CategoriesState())
    val state: State<CategoriesState> = _state

    fun onEvent(event: CategoriesUIEvents) {
        when (event) {
            is CategoriesUIEvents.SearchRecipeChanged -> {
                // TODO
            }

            is CategoriesUIEvents.SearchRecipeResults -> {
                // TODO
            }

            is CategoriesUIEvents.SignOff -> {
                handleLogOut()
            }

            is CategoriesUIEvents.DisplayOptionsMenu, CategoriesUIEvents.DismissOptionsMenu -> {
                _state.value =
                    _state.value.copy(displayOptionsMenu = !_state.value.displayOptionsMenu)
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