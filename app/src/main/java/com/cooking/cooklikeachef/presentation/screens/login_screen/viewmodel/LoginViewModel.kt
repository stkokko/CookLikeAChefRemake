package com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.LogIn
import com.cooking.cooklikeachef.presentation.screens.login_screen.events.LoginUIEvents
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logIn: LogIn
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state


    fun onEvent(event: LoginUIEvents) {
        when (event) {
            is LoginUIEvents.EmailChanged -> {
                if (!Patterns.EMAIL_ADDRESS.matcher(event.email).matches())
                    _state.value = _state.value.copy(isEmailValid = true)
                _state.value = _state.value.copy(email = event.email)
            }

            is LoginUIEvents.PasswordChanged -> {
                if (event.password.length >= 6)
                    _state.value = _state.value.copy(isPasswordValid = true)
                _state.value = _state.value.copy(password = event.password)
            }

            is LoginUIEvents.DialogDismissed, LoginUIEvents.OpenDialogClicked -> {
                _state.value = _state.value.copy(openDialog = !_state.value.openDialog)
            }

            is LoginUIEvents.SignIn -> {
                handleLogin(event.email, event.password)
            }

            is LoginUIEvents.ShowPasswordClick -> {
                _state.value =
                    _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
            }
        }
    }

    private fun handleLogin(email: String, password: String) {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.value = _state.value.copy(isEmailValid = false)
            return
        }

        if (password.length < 6) {
            _state.value = _state.value.copy(isPasswordValid = false)
            return
        }

        _state.value = _state.value.copy(isEmailValid = true)
        _state.value = _state.value.copy(isPasswordValid = true)

        logIn(email, password).onEach { result ->

            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(isLoading = false, isLoggedIn = result.data ?: false)
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoggedIn = false,
                        isLoading = false,
                        errorMessage = result.message ?: ""
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

}