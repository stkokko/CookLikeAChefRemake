package com.cooking.cooklikeachef.presentation.screens.favourites_screen.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.GetFavouriteRecipes
import com.cooking.cooklikeachef.domain.use_cases.LogOut
import com.cooking.cooklikeachef.presentation.screens.categories_screen.events.CategoriesUIEvents
import com.cooking.cooklikeachef.presentation.screens.favourites_screen.events.FavouritesUIEvents
import com.cooking.cooklikeachef.presentation.screens.main_screen.viewmodel.MainState
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

            is FavouritesUIEvents.SignOff -> {
                handleLogOut()
            }

            is FavouritesUIEvents.DisplayOptionsMenu, FavouritesUIEvents.OptionsMenuDismissed -> {
                _state.value =
                    _state.value.copy(displayOptionsMenu = !_state.value.displayOptionsMenu)
            }
        }
    }

    private fun handleLogOut() {
        logOut().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(isLoading = false, isLoggedIn = result.data ?: true)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        errorMessage = result.message ?: ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}