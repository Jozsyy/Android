package com.tasty.recipesapp.repository.recipe.model

data class RecipeModel (
    val name: String,
    val description: String? = "",
    val thumbnail_url:String,
    val time: RecipeTime
)

data class RecipeTime(
    val start_time: Int,
    val end_time:Int
)