package com.cooking.cooklikeachef.presentation.screens.main_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.GetLatestRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
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
                    Log.d(
                        "initLatestRecipes",
                        "recipesList: ${_state}"
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(latestRecipesList = result.data ?: emptyList(), isLoading = false)
                    Log.d(
                        "initLatestRecipes",
                        "recipesList: ${_state}"
                    )
                }

                is Resource.Error -> {
                    _state.value = MainScreenState(message = result.message ?: "An unexpected error occurred.")
                }
            }
        }.launchIn(viewModelScope)
    }
}