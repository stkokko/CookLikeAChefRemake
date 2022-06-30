package com.cooking.cooklikeachef.domain.use_cases

import com.cooking.cooklikeachef.domain.model.Recipe

class UpdateFavouriteRecipe {

    operator fun invoke(
        favouriteRecipes: List<Recipe>,
        favouriteRecipeToUpdate: Recipe
    ): List<Recipe> {
        // TODO
        return favouriteRecipes
    }

}