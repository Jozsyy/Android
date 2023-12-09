package com.tasty.recipesapp.repository.recipe.model

data class RecipeDTO (
    val id: Int,
    val name: String,
    val description: String? = "Default",
    val thumbnail_url: String,
    val user_ratings: UserRatingsDTO,
    val instructions: List<InstructionDTO>
)

fun RecipeDTO.toModel(): RecipeModel{
    return RecipeModel(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnail_url = this.thumbnail_url,
        user_ratings = this.user_ratings.toModel(),
        instructions = this.instructions.toModelList()
    )
}

fun List<RecipeDTO>.toModelList():List<RecipeModel> =
    this.map { it.toModel() }




