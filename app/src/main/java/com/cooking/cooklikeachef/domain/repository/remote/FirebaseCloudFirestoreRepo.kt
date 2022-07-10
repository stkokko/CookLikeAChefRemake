package com.cooking.cooklikeachef.domain.repository.remote

import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseCloudFirestoreRepo {

    fun getRecipes(): Flow<Resource<List<Recipe>>>

    suspend fun getLatestRecipes(): List<Recipe>

    fun getFavouriteRecipes(): Flow<Resource<List<Recipe>>>

}