package com.cooking.cooklikeachef.data.repository

import com.cooking.cooklikeachef.domain.repository.FirebaseAuthRepo
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseAuthRepoImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthRepo {

    override fun logIn(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun register(email: String, password: String, confirmPassword: String) {
        TODO("Not yet implemented")
    }

    override fun logOut() {
        TODO("Not yet implemented")
    }

}