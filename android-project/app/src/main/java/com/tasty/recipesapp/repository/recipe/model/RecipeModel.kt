package com.tasty.recipesapp.repository.recipe.model

import android.text.Editable
import com.google.gson.Gson

data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String? = "",
    val thumbnail_url:String,
    val user_ratings: UserRatingsModel,
    val instructions: List<InstructionModel>,
    val video_url: String? = ""
)


//Use this one before we delete a recipe
fun RecipeModel.toRecipeEntity(): RecipeEntity {
    // Konvertáljuk a RecipeModel értékeit RecipeEntity értékekké
    val gson = Gson()
    val json = gson.toJson(this)

    return RecipeEntity(
        internalId = this.id.toLong(),
        json = json
    )
}

//Use this one when we insert a new recipe (autogenerating id)
fun RecipeModel.toRecipeEntity_generateID(): RecipeEntity {
    val gson = Gson()
    val json = gson.toJson(this)

    return RecipeEntity(
        json = json
    )
}