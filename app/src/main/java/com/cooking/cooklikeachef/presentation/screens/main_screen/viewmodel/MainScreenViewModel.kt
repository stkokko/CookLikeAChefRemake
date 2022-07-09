package com.cooking.cooklikeachef.presentation.screens.main_screen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.GetLatestRecipes
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getLatestRecipes: GetLatestRecipes
) : ViewModel() {

    private val _state = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state

    init {
        initLatestRecipes()
    }

    private fun initLatestRecipes() {
        getLatestRecipes().onEach { result ->

            when (result) {

                is Resource.Loading -> {
                    _state.value = MainScreenState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(latestRecipesList = result.data ?: emptyList(), isLoading = false)
                }

                is Resource.Error -> {
                    _state.value = MainScreenState(message = result.message ?: "An unexpected error occurred.", isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}