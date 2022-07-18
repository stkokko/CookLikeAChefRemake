package com.cooking.cooklikeachef.presentation.screens.home_screen.viewmodel

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.GetLatestRecipes
import com.cooking.cooklikeachef.domain.use_cases.LogOut
import com.cooking.cooklikeachef.presentation.screens.home_screen.events.HomeUIEvents
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestRecipes: GetLatestRecipes,
    private val logOut: LogOut
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        initLatestRecipes()
    }

    private fun initLatestRecipes() {
        getLatestRecipes().onEach { result ->

            when (result) {

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        latestRecipesList = emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        latestRecipesList = result.data ?: emptyList(),
                        errorMessage = ""
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        latestRecipesList = emptyList(),
                        errorMessage = result.message ?: "An unexpected error occurred."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeUIEvents) {
        when (event) {

            is HomeUIEvents.ContactUs -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("cooklikeachef96@gmail.com"))
                    type = "message/rfc822"
                    //type = "plain/text"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                ContextCompat.startActivity(event.context, shareIntent, null)
            }

            is HomeUIEvents.SignOff -> {
                handleLogOut()
            }

            is HomeUIEvents.DisplayOptionsMenu, HomeUIEvents.DismissOptionsMenu -> {
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