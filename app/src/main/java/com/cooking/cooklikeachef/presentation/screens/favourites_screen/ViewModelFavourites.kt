package com.cooking.cooklikeachef.presentation.screens.favourites_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.GetFavouriteRecipes
import com.cooking.cooklikeachef.presentation.screens.main_screen.MainScreenState
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelFavourites @Inject constructor(
    private val getFavouriteRecipes: GetFavouriteRecipes
) : ViewModel() {

    private val _state = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state

    init {
        initFavouriteRecipes()
    }

    private fun initFavouriteRecipes() {
        getFavouriteRecipes().onEach { result ->

            when(result) {

                is Resource.Loading -> {
                    _state.value = MainScreenState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(favouriteRecipesList = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = MainScreenState(message = result.message ?: "An unexpected error occurred.")
                }
            }
        }.launchIn(viewModelScope)
    }

}