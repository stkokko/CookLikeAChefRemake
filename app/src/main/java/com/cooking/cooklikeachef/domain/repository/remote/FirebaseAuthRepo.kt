package com.cooking.cooklikeachef.domain.repository.remote

import com.google.firebase.auth.AuthResult

interface FirebaseAuthRepo {

    suspend fun logIn(email: String, password: String) : AuthResult?

    fun register(email: String, password: String, confirmPassword: String)

    fun logOut()

}