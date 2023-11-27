package com.tasty.recipesapp.repository.recipe.model

data class UserRatingsDTO (
    val count_positive: Int,
    val count_negative: Int,
    var score: Float
)

fun UserRatingsDTO.toModel(): UserRatingsModel{
    return UserRatingsModel(
        score=this.score
    )
}

