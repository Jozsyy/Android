package com.tasty.recipesapp.repository.recipe.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipe")
data class RecipeEntity (
    @PrimaryKey(autoGenerate = true)
    val internalId: Long = 0L, // Room will handle generating this ID

    @SerializedName("json_data")
    val json: String
)

fun RecipeEntity.toRecipeModel(): RecipeModel {
    // Konvertáljuk a RecipeEntity értékeit RecipeModel értékekké
    val gson = Gson()
    val recipeModel = gson.fromJson(json, RecipeModel::class.java)

    return recipeModel
}