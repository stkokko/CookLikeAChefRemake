package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.repository.local.DataStoreRepo
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseAuthRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogIn @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo,
    private val dataStoreRepo: DataStoreRepo
) {
    operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val authResult = firebaseAuthRepo.logIn(email, password)
            if (authResult?.user == null) emit(Resource.Error(null, "Could not login"))
            else {
                dataStoreRepo.setUserLoginState(true)
                emit(Resource.Success(true))
            }
        } catch (e: Exception) {
            emit(Resource.Error(null, "An unexpected error occurred."))
        }
    }
}