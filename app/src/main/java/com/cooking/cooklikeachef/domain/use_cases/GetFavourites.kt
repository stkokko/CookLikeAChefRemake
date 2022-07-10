package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteRecipes @Inject constructor(
    private val firebaseCloudFirestoreRepo: FirebaseCloudFirestoreRepo
) {
    operator fun invoke(): Flow<Resource<List<Recipe>>> =
        firebaseCloudFirestoreRepo.getFavouriteRecipes()
}