package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLatestRecipes @Inject constructor(
    private val firebaseCloudFirestoreRepo: FirebaseCloudFirestoreRepo
) {
    operator fun invoke(): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading())
        try {
            val result = firebaseCloudFirestoreRepo.getRecipes()
            if (result.isNotEmpty())
                emit(Resource.Success(result.subList(0, 4)))
            else
                emit(Resource.Error(emptyList(), "No data retrieved from database."))
        } catch (e: Exception) {
            emit(Resource.Error(emptyList(), "An unexpected error occurred."))
        }
    }.flowOn(Dispatchers.IO)
}