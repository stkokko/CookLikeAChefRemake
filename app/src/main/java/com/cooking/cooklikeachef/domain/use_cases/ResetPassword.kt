package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.repository.remote.FirebaseAuthRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResetPassword @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo
){
    operator fun invoke(email: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            when(val result = firebaseAuthRepo.resetPassword(email)) {
                is Resource.Success -> {
                    emit(Resource.Success(true))
                }

                else -> {
                    emit(Resource.Error(null, result.message))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(null, "An unexpected error occurred."))
        }
    }
}