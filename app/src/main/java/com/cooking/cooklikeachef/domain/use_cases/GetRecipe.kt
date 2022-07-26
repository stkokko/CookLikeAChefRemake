package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRecipe @Inject constructor(
    private val firebaseCloudFirestoreRepo: FirebaseCloudFirestoreRepo
) {
    operator fun invoke(recipeId: String): Flow<Resource<Recipe>> = flow {
        emit(Resource.Loading())
        try {
            val result = firebaseCloudFirestoreRepo.getRecipe(recipeId)
            if (result != null)
                emit(Resource.Success(result))
            else
                emit(Resource.Error(message = "No data retrieved from database."))
        } catch (e: Exception) {
            emit(Resource.Error(message = "An unexpected error occurred."))
        }
    }.flowOn(Dispatchers.IO)
}