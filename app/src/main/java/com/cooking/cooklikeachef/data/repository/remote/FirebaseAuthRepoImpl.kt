package com.cooking.cooklikeachef.data.repository.remote

import android.util.Log
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseAuthRepo
import com.cooking.cooklikeachef.util.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepoImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthRepo {

    override suspend fun logIn(email: String, password: String): AuthResult? {
        return try {
            val data = auth
                .signInWithEmailAndPassword(email, password)
                .await()
            data
        } catch (e: Exception) {
            Log.d("RepositoryImpl", "logIn: ${e.message}")
            null
        }
    }

    override suspend fun register(email: String, password: String): Resource<AuthResult?> {
        return try {
            val data = auth
                .createUserWithEmailAndPassword(email, password)
                .await()
            Resource.Success(data)
        } catch (e: FirebaseAuthException) {
            Resource.Error(null, "The email address is already in use by another account.")
        } catch (e: Exception) {
            Resource.Error(null, "A network error has occurred.")
        }
    }

    override suspend fun logOut(): Resource<Unit?> {
        return try {
            val data = auth.signOut()
            Resource.Success(data)
        } catch (e: Exception) {
            Resource.Error(null, "A network error has occurred.")
        }
    }

}