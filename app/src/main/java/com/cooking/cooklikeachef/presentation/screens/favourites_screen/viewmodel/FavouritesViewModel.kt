package com.cooking.cooklikeachef.presentation.screens.favourites_screen.viewmodel

import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.GetFavouriteRecipes
import com.cooking.cooklikeachef.domain.use_cases.LogOut
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.events.FavouritesUIEvents
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouriteRecipes: GetFavouriteRecipes,
    private val logOut: LogOut
) : ViewModel() {

    private val _state = mutableStateOf(FavouritesState())
    val state: State<FavouritesState> = _state

    init {
        initFavouriteRecipes()
    }

    private fun initFavouriteRecipes() {
//        getFavouriteRecipes().onEach { result ->
//
//            when(result) {
//
//                is Resource.Loading -> {
//                    _state.value = MainState(isLoading = true)
//                }
//
//                is Resource.Success -> {
//                    _state.value = _state.value.copy(favouriteRecipesList = result.data ?: emptyList())
//                }
//
//                is Resource.Error -> {
//                    _state.value = MainState(errorMessage = result.message ?: "An unexpected error occurred.")
//                }
//            }
//        }.launchIn(viewModelScope)
    }

    fun onEvent(event: FavouritesUIEvents) {
        when (event) {
            is FavouritesUIEvents.SearchFavouriteRecipeChanged -> {
                // TODO
            }

            is FavouritesUIEvents.SearchFavouriteRecipeResults -> {
                // TODO
            }

            is FavouritesUIEvents.ContactUs -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("cooklikeachef96@gmail.com"))
                    type = "message/rfc822"
                    //type = "plain/text"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                ContextCompat.startActivity(event.context, shareIntent, null)
            }

            is FavouritesUIEvents.SignOff -> {
                handleLogOut()
            }

            is FavouritesUIEvents.DisplayOptionsMenu, FavouritesUIEvents.DismissOptionsMenu -> {
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
                        _state.value.copy(isLoading = true, isLoggedIn = false, errorMessage = "")
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