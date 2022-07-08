package com.cooking.cooklikeachef.util

import com.cooking.cooklikeachef.data.remote.dto.RecipeDto
import com.cooking.cooklikeachef.domain.model.Ingredient
import com.cooking.cooklikeachef.domain.model.Recipe

object Mappers {

    fun recipeDtoToModel(recipeDto: RecipeDto): Recipe {
        val ingredients = mutableListOf<Ingredient>()
        recipeDto.ingredients.values.forEach { ingredient ->
            ingredients.add(Ingredient(ingredient[0], ingredient[1]))
        }

        return Recipe(
            name = recipeDto.name,
            category = recipeDto.category,
            imageURL = recipeDto.imageURL,
            ingredients = ingredients,
            steps = recipeDto.steps,
            comment = recipeDto.comment,
            language = recipeDto.language,
            timestamp = recipeDto.timestamp
        )
    }
}