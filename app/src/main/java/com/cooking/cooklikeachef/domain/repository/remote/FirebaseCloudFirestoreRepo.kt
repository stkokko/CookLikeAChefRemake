package com.cooking.cooklikeachef.domain.repository.remote

import com.cooking.cooklikeachef.domain.model.Recipe

interface FirebaseCloudFirestoreRepo {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipe(recipeId: String): Recipe?
    suspend fun getFavouriteRecipes(): List<Recipe>
}