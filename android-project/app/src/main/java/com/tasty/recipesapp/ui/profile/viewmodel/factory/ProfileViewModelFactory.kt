package com.tasty.recipesapp.ui.profile.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.repository.recipe.RecipeRepository

class ProfileViewModelFactory(val recipeRepository: RecipeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModelFactory(recipeRepository) as T
    }
}