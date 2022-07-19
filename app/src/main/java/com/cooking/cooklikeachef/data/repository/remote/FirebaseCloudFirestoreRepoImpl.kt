package com.cooking.cooklikeachef.data.repository.remote

import android.util.Log
import com.cooking.cooklikeachef.data.remote.dto.RecipeDto
import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.util.Mappers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.Exception

class FirebaseCloudFirestoreRepoImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : FirebaseCloudFirestoreRepo {

    override suspend fun getRecipes(): List<Recipe> {
        return try {
            val recipesDto = db.collection("Recipes").get().await().toObjects(RecipeDto::class.java)
            val recipes = recipesDto.map {
                Mappers.recipeDtoToModel(it)
            }
            recipes
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getFavouriteRecipes(): List<Recipe> {
        return try {
            if (auth.currentUser != null) {
//                val recipesDto: List<Map<String, Any>> =
//                    db.collection("Favourites").document(auth.currentUser!!.uid).get().await()
//                        .get("recipes") as List<Map<String, Any>>
//
//                val dto = recipesDto.
//                Log.d("ImplGetFavouriteRecipes", "recipesDto: ${dto}")
//                Log.d("ImplGetFavouriteRecipes", "recipes: ${dto[0]}")
                emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }


}