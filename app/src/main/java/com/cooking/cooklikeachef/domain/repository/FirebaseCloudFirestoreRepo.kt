package com.cooking.cooklikeachef.domain.repository

import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.util.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseCloudFirestoreRepo {

    fun getRecipes(): Flow<Resource<List<Recipe>>>

    fun getLatestRecipes(): Flow<Resource<List<Recipe>>>

    fun getFavouriteRecipes(): Flow<Resource<List<Recipe>>>

}