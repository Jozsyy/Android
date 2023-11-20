package com.tasty.recipesapp.repository.recipe.model

data class RecipeDTO (
    val id: Int,
    val name: String,
    val description: String? = "Default",
    val thumbnailUrl: String,
    val position: Int,
    val start_time: Int,
    val end_time: Int,
    val temperature: String?
)

fun RecipeDTO.toModel(): RecipeModel{
    return RecipeModel(
        name = this.name,
        description = this.description,
        time=RecipeTime(
            start_time = this.start_time,
            end_time = this.end_time
        ),
        thumbnail_url = this.thumbnailUrl
    )
}

fun List<RecipeDTO>.toModelList():List<RecipeModel> =
    this.map { it.toModel() }




