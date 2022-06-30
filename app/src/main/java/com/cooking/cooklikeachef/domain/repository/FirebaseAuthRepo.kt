package com.cooking.cooklikeachef.domain.repository

interface FirebaseAuthRepo {

    fun logIn(email: String, password: String)

    fun register(email: String, password: String, confirmPassword: String)

    fun logOut()

}