package com.tasty.recipesapp.repository.recipe.model

data class UserRatingsDTO (
    val count_positive: Int,
    val count_negative: Int,
    val score: Float
)

//fun UserRatingsDTO.toModel(): RecipeModel{
//
//}