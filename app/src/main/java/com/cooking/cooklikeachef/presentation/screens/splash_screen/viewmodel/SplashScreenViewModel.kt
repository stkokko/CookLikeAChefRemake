package com.cooking.cooklikeachef.presentation.screens.splash_screen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.GetUserLoginState
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getUserLoginState: GetUserLoginState
) : ViewModel() {

    private val _state = mutableStateOf(SplashScreenState())
    val state: State<SplashScreenState> = _state

    init {
        initUserLoginState()
    }

    private fun initUserLoginState() {
        getUserLoginState().onEach { result ->

            when (result) {

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(isLoggedIn = result.data ?: false, isLoading = false)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = result.message ?: "An unexpected error occurred."
                    )
                }

            }

        }.launchIn(viewModelScope)
    }

}