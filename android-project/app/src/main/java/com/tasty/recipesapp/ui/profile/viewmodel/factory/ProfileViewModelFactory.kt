package com.tasty.recipesapp.ui.profile.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeDetailViewModel

class ProfileViewModelFactory(val recipeRepository: RecipeRepository): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return ProfileViewModelFactory(recipeRepository) as T
//    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(recipeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}