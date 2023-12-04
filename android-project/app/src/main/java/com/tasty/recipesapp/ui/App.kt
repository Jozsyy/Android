package com.tasty.recipesapp.ui

import android.app.Application
import com.tasty.recipesapp.repository.recipe.RecipeRepository

class App : Application(){
    //private val database by lazy { RecipeDatabase.getDatabase(this) }
    val repository by lazy {RecipeRepository} //val repository by lazy {RecipeRepository(database.recipeDao())}
}