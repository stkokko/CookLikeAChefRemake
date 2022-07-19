package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavouriteRecipes @Inject constructor(
    private val firebaseCloudFirestoreRepo: FirebaseCloudFirestoreRepo
) {
    operator fun invoke(): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading())
        try {
            val result = firebaseCloudFirestoreRepo.getFavouriteRecipes()
            if (result.isNotEmpty())
                emit(Resource.Success(result))
            else
                emit(Resource.Error(emptyList(), "No data retrieved from database."))
        } catch (e: Exception) {
            emit(Resource.Error(emptyList(), "An unexpected error occurred."))
        }
    }
}