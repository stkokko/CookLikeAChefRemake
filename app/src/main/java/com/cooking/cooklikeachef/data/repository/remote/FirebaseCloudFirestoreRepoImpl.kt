package com.cooking.cooklikeachef.data.repository.remote

import com.cooking.cooklikeachef.data.remote.dto.RecipeDto
import com.cooking.cooklikeachef.domain.model.Comment
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
            val result = db.collection("Recipes").get().await()
            val recipesDto = result.map {
                RecipeDto(
                    id = it.id,
                    name = it.data["name"] as? String ?: "",
                    category = it.data["category"] as? String ?: "",
                    imageURL = it.data["imageURL"] as? String ?: "",
                    ingredients = it.data["ingredients"] as? Map<String, List<String>>
                        ?: emptyMap(),
                    steps = it.data["steps"] as? String ?: "",
                    comments = it.data["comments"] as? List<Comment> ?: emptyList(),
                    language = it.data["language"] as? String ?: "",
                    timestamp = it.data["timestamp"] as? String ?: ""
                )
            }
            val recipes = recipesDto.map {
                Mappers.recipeDtoToModel(it)
            }
            recipes
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getRecipe(recipeId: String): Recipe? {
        return try {
            val recipeDto = db.collection("Recipes").document(recipeId).get().await()
                .toObject(RecipeDto::class.java)
            val recipe = recipeDto?.let { Mappers.recipeDtoToModel(it) }
            recipe
        } catch (e: Exception) {
            null
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
                    val snapshot = db.collection("Recipes").document(id.trim()).get().await()
                    recipesDto.add(
                        RecipeDto(
                            id = id.trim(),
                            name = snapshot.data?.get("name") as? String ?: "",
                            category = snapshot.data?.get("category") as? String ?: "",
                            imageURL = snapshot.data?.get("imageURL") as? String ?: "",
                            ingredients = snapshot.data?.get("ingredients") as? Map<String, List<String>>
                                ?: emptyMap(),
                            steps = snapshot.data?.get("steps") as? String ?: "",
                            comments = snapshot.data?.get("comments") as? List<Comment>
                                ?: emptyList(),
                            language = snapshot.data?.get("language") as? String ?: "",
                            timestamp = snapshot.data?.get("timestamp") as? String ?: ""
                        )
                    )
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