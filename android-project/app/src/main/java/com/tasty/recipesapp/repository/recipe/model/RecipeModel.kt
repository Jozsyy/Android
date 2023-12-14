package com.tasty.recipesapp.repository.recipe.model

import android.text.Editable

data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String? = "",
    val thumbnail_url:String,
    val user_ratings: UserRatingsModel,
    val instructions: List<InstructionModel>,
    val video_url: String? = ""
)