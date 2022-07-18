package com.cooking.cooklikeachef.presentation.screens.register_screen.viewmodel

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.Register
import com.cooking.cooklikeachef.presentation.screens.register_screen.events.RegisterUIEvents
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val register: Register
) : ViewModel() {
    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    fun onEvent(event: RegisterUIEvents) {
        when (event) {
            is RegisterUIEvents.EmailChanged -> {
                if (Patterns.EMAIL_ADDRESS.matcher(event.email).matches())
                    _state.value = _state.value.copy(isEmailValid = true)
                else
                    _state.value = _state.value.copy(isEmailValid = false)
                _state.value = _state.value.copy(email = event.email)
            }

            is RegisterUIEvents.PasswordChanged -> {
                if (event.password.length >= 6)
                    _state.value = _state.value.copy(isPasswordValid = true)
                else
                    _state.value = _state.value.copy(isPasswordValid = false)
                _state.value = _state.value.copy(password = event.password)
            }

            is RegisterUIEvents.ConfirmPasswordChanged -> {
                if (event.confirmPassword == event.password)
                    _state.value = _state.value.copy(isConfirmPasswordValid = true)
                else
                    _state.value = _state.value.copy(isConfirmPasswordValid = false)
                _state.value = _state.value.copy(confirmPassword = event.confirmPassword)
            }

            is RegisterUIEvents.CreateUser -> {
                handleRegister(event.email, event.password)
            }

            is RegisterUIEvents.ShowPasswordClick -> {
                _state.value =
                    _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
            }

            is RegisterUIEvents.ShowConfirmPasswordClick -> {
                _state.value =
                    _state.value.copy(isConfirmPasswordVisible = !_state.value.isConfirmPasswordVisible)
            }

            is RegisterUIEvents.OpenExitAppDialog, RegisterUIEvents.DismissExitAppDialog -> {
                _state.value = _state.value.copy(
                    openExitAppDialog = !_state.value.openExitAppDialog
                )
            }
        }
    }

    private fun handleRegister(email: String, password: String) {
        register(email, password).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value =
                        _state.value.copy(isLoading = true, isRegistered = false, errorMessage = "")
                }

                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            isRegistered = result.data ?: false,
                            errorMessage = ""
                        )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isRegistered = false,
                        isLoading = false,
                        errorMessage = result.message ?: "An unexpected error occurred."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}