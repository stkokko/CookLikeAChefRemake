package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.repository.local.DataStoreRepo
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseAuthRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Register @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo,
    private val dataStoreRepo: DataStoreRepo
) {
    operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            when (val authResult = firebaseAuthRepo.register(email, password)) {
                is Resource.Success -> {
                    dataStoreRepo.setUserLoginState(true)
                    emit(Resource.Success(true))
                }

                else -> {
                    emit(Resource.Error(null, authResult.message))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(null, "An unexpected error occurred."))
        }
    }
}