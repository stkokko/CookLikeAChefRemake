package com.cooking.cooklikeachef.presentation.screens.login_screen.viewmodel

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cooking.cooklikeachef.domain.use_cases.LogIn
import com.cooking.cooklikeachef.domain.use_cases.ResetPassword
import com.cooking.cooklikeachef.presentation.screens.login_screen.events.LoginUIEvents
import com.cooking.cooklikeachef.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logIn: LogIn,
    private val resetPassword: ResetPassword
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state


    fun onEvent(event: LoginUIEvents) {
        when (event) {
            is LoginUIEvents.EmailChanged -> {
                if (Patterns.EMAIL_ADDRESS.matcher(event.email).matches())
                    _state.value = _state.value.copy(isEmailValid = true)
                else
                    _state.value = _state.value.copy(isEmailValid = false)
                _state.value = _state.value.copy(email = event.email)
            }

            is LoginUIEvents.PasswordChanged -> {
                if (event.password.length >= 6)
                    _state.value = _state.value.copy(isPasswordValid = true)
                else
                    _state.value = _state.value.copy(isPasswordValid = false)
                _state.value = _state.value.copy(password = event.password)
            }

            is LoginUIEvents.DialogEmailChanged -> {
                if (Patterns.EMAIL_ADDRESS.matcher(event.dialogEmail).matches())
                    _state.value = _state.value.copy(isDialogEmailValid = true)
                else
                    _state.value = _state.value.copy(isDialogEmailValid = false)
                _state.value = _state.value.copy(dialogEmail = event.dialogEmail)
            }

            is LoginUIEvents.SignIn -> {
                handleLogin(event.email, event.password)
            }

            is LoginUIEvents.ResetPassword -> {
                handleResetPassword(event.email)
            }

            // TODO: should we reset both the email and password of login form,
            // TODO: as when we open and then close the dialog if the fields
            // TODO: were not empty then they would keep their values

            is LoginUIEvents.ResetDialogEmail -> {
                _state.value = state.value.copy(dialogEmail = "")
            }

            is LoginUIEvents.ResetErrorMessage -> {
                _state.value = _state.value.copy(errorMessage = "")
            }

            is LoginUIEvents.ShowPasswordClick -> {
                _state.value =
                    _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
            }

            is LoginUIEvents.DialogDismissed, LoginUIEvents.OpenDialogClicked -> {
                _state.value = _state.value.copy(openDialog = !_state.value.openDialog)
            }
        }
    }

    private fun handleLogin(email: String, password: String) {
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

    private fun handleResetPassword(email: String) {
        resetPassword(email).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            isResetPasswordSent = result.data ?: false
                        )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isResetPasswordSent = false,
                        errorMessage = result.message ?: ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}