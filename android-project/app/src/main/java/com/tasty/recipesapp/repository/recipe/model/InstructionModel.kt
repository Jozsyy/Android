package com.tasty.recipesapp.repository.recipe.model

data class  InstructionModel(
    val id: Int,
    val display_text: String,
    val time: InstructionTime,
    val temperature: String?
)


data class InstructionTime(
    val start_time: Int,
    val end_time: Int )
