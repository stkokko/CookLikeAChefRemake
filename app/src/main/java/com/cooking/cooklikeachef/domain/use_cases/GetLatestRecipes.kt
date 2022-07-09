package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.repository.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLatestRecipes @Inject constructor(
    private val firebaseCloudFirestoreRepo: FirebaseCloudFirestoreRepo
) {
    operator fun invoke(): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading())
        try {
            val recipes = firebaseCloudFirestoreRepo.getLatestRecipes()
            emit(Resource.Success(recipes))
        } catch (e: Exception) {
            emit(Resource.Error(null, "An unexpected error occurred."))
        }
    }
}