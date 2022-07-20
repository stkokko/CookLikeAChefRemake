package com.cooking.cooklikeachef.data.repository.remote

import com.cooking.cooklikeachef.data.remote.dto.RecipeDto
import com.cooking.cooklikeachef.domain.model.Recipe
import com.cooking.cooklikeachef.domain.repository.remote.FirebaseCloudFirestoreRepo
import com.cooking.cooklikeachef.util.Mappers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
            if (auth.currentUser != null && !auth.currentUser?.uid.isNullOrEmpty()) {
                val recipesDto = mutableListOf<RecipeDto>()
                val favouriteRecipesIds =
                    db.collection("Favourites").document(auth.currentUser!!.uid).get().await()
                        .get("recipes") as? List<String>
                favouriteRecipesIds?.forEach { id ->
                    db.collection("Recipes").document(id.trim()).get().await()
                        .toObject(RecipeDto::class.java)?.let {
                        recipesDto.add(it)
                    }
                }

                val favouriteRecipes = recipesDto.map {
                    Mappers.recipeDtoToModel(it)
                }
                favouriteRecipes
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }


}