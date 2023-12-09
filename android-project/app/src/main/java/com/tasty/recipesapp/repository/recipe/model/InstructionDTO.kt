package com.tasty.recipesapp.repository.recipe.model

data class InstructionDTO(
    val appliance: String?,
    val end_time: Int,
    val temperature: String?,
    val id: Int,
    val position: Int,
    val display_text: String,
    val start_time: Int )

fun InstructionDTO.toModel():InstructionModel{
    return InstructionModel(
        id = this.id,
        display_text = this.display_text,
        time = InstructionTime(this.start_time,this.end_time),
        temperature = this.temperature
    )
}

fun List<InstructionDTO>.toModelList():List<InstructionModel> =
    this.map{ it.toModel()}

