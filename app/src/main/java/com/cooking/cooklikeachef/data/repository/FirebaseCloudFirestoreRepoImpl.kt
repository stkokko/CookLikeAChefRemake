package com.cooking.cooklikeachef.data.repository

import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.repository.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FirebaseCloudFirestoreRepoImpl @Inject constructor(
    private val db: FirebaseFirestore
) : FirebaseCloudFirestoreRepo {

    override fun getRecipes(): Flow<Resource<List<Recipe>>> {
        TODO("Not yet implemented")
    }

    override fun getFavouriteRecipes(): Flow<Resource<List<Recipe>>> {
        TODO("Not yet implemented")
    }

}