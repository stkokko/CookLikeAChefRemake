package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.repository.local.DataStoreRepo
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseAuthRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogOut @Inject constructor(
    private val firebaseAuthRepo: FirebaseAuthRepo,
    private val dataStoreRepo: DataStoreRepo
) {
    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            when (val authResult = firebaseAuthRepo.logOut()) {
                is Resource.Success -> {
                    dataStoreRepo.setUserLoginState(false)
                    emit(Resource.Success(false))
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