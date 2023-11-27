package com.tasty.recipesapp.repository.recipe.model

data class RecipeModel (
    val id: Int,
    val name: String,
    val description: String? = "",
    val thumbnail_url:String,
    val user_ratings: UserRatingsModel
)