package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.repository.local.DataStoreRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserLoginState @Inject constructor(
    private val dataStoreRepo: DataStoreRepo
) {

    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val isUserLogged = dataStoreRepo.getUserLoginState()
            emit(Resource.Success(isUserLogged))
        } catch (e: Exception) {
            emit(Resource.Error(false, e.message))
        }
    }

}