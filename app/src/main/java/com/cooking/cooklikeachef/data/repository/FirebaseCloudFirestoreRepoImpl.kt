package com.cooking.cooklikeachef.data.repository

import android.util.Log
import com.cooking.cooklikeachef.data.remote.dto.RecipeDto
import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.repository.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.util.Mappers
import com.cooking.cooklikeachef.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class FirebaseCloudFirestoreRepoImpl @Inject constructor(
    private val db: FirebaseFirestore
) : FirebaseCloudFirestoreRepo {

    override fun getRecipes(): Flow<Resource<List<Recipe>>> = flow {

        emit(Resource.Loading())

        try {
            val recipesList = db.collection("Recipes").get().await()?.toObjects(Recipe::class.java)
            emit(Resource.Success(recipesList))
        } catch (e: Exception) {
            Log.d("RecipesRepoImpl", "${e.message}")
            emit(Resource.Error(message = "Something went wrong, check your internet connection."))
        }

    }.flowOn(Dispatchers.IO)

    override fun getLatestRecipes(): Flow<Resource<List<Recipe>>> = flow {

        emit(Resource.Loading())
        Log.d("getLatestRecipes", "line 37")
        try {
            val recipesDtoList =
                db.collection("Recipes").get().await().toObjects(RecipeDto::class.java)
            val recipesList = recipesDtoList.map {
                Mappers.recipeDtoToModel(it)
            }
            val latestRecipesList = recipesList.sortedByDescending { recipe ->
                recipe.timestamp
            }
            emit(Resource.Success(latestRecipesList.subList(0, 4)))
        } catch (e: Exception) {
            Log.d("LatestRecipesRepoImpl", "${e.message}")
            emit(Resource.Error(message = "Something went wrong, check your internet connection."))
        }
    }.flowOn(Dispatchers.IO)

    override fun getFavouriteRecipes(): Flow<Resource<List<Recipe>>> =
        flow<Resource<List<Recipe>>> {

            emit(Resource.Loading())

            try {
                // TODO: check the presentation of every document in the Favourites collection
                // val favouriteRecipes = db.collection("Favourites").document("connectedUserId").get().await()
            } catch (e: Exception) {
                Log.d("FavouritesRepoImpl", "${e.message}")
                emit(Resource.Error(message = "Something went wrong, check your internet connection."))
            }
        }.flowOn(Dispatchers.IO)

}