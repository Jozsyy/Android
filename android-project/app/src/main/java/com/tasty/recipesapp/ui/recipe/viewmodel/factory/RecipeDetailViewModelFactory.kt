package com.tasty.recipesapp.ui.recipe.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.repository.recipe.RecipeRepository

class RecipeDetailViewModelFactory (val recipeRepository: RecipeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeDetailViewModelFactory(recipeRepository) as T
    }
}