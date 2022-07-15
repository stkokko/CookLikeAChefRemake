package com.cooking.cooklikeachef.domain.repository.remote

import com.cooking.cooklikeachef.util.Resource
import com.google.firebase.auth.AuthResult

interface FirebaseAuthRepo {

    suspend fun logIn(email: String, password: String) : AuthResult?

    suspend fun register(email: String, password: String) : Resource<AuthResult?>

    suspend fun logOut() : Resource<Unit?>

    suspend fun resetPassword(email: String) : Resource<Void?>

}