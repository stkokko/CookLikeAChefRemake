package com.cooking.cooklikeachef.data.repository

import android.util.Log
import com.cooking.cooklikeachef.domain.repository.FirebaseAuthRepo
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepoImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthRepo {

    override suspend fun logIn(email: String, password: String) : AuthResult? {
        return try {
            val data = auth
                .signInWithEmailAndPassword(email, password)
                .await()
            data
        } catch (e : Exception) {
            Log.d("Repository", "logIn: ${e.message}")
            null
        }
    }

    override fun register(email: String, password: String, confirmPassword: String) {
        TODO("Not yet implemented")
    }

    override fun logOut() {
        TODO("Not yet implemented")
    }

}