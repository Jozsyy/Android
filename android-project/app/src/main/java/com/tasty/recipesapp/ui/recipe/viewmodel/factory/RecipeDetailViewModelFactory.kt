package com.tasty.recipesapp.ui.recipe.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeDetailViewModel
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel

class RecipeDetailViewModelFactory(private val recipeRepository: RecipeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecipeDetailViewModel(recipeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}