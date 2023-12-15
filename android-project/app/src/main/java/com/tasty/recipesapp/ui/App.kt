package com.tasty.recipesapp.ui

import android.app.Application
import com.tasty.recipesapp.repository.recipe.RecipeDatabase
import com.tasty.recipesapp.repository.recipe.RecipeRepository

class App : Application(){
    private val database by lazy { RecipeDatabase.getDatabase(this) }
    val repository by lazy {RecipeRepository(database.recipeDao())}//val repository by lazy {RecipeRepository}
}