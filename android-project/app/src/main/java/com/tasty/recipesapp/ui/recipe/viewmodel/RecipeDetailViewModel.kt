package com.tasty.recipesapp.ui.recipe.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipeDetailViewModel {

    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()

    fun fetchRecipeDetail(recipeId: Int, context: Context){
        //val recipeDatabase = RecipeDatabase.getDatabase(context).recipeDao()
        val recipe = RecipeRepository.getRecipe(recipeId)
        this.recipe.value = recipe
    }
}