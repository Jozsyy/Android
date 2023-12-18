package com.tasty.recipesapp.ui.recipe.viewmodel

import android.content.Context

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.launch

class RecipeListViewModel(private val recipeRepository: RecipeRepository): ViewModel() {
   // private val recipeRepository = RecipeRepository

    //Live data member
    var recipeList: MutableLiveData<List<RecipeModel>> = MutableLiveData()

    fun fetchRecipeData(context: Context){
        recipeList.value = recipeRepository.getRecipes(context)
    }

    fun getAllRecipesFromApi(){
        viewModelScope.launch {
            recipeList.value = recipeRepository.getRecipesFromApi("0","20",null)
        }
    }

    //Insert to myRecipelist local
    fun insertRecipe(recipeModel: RecipeModel) {
        recipeRepository.insertRecipe(recipeModel)
    }

    //insert to DB
//    fun insertRecipe(recipeEntity: RecipeEntity){
//        viewModelScope.launch {
//            recipeRepository.insertRecipeDB(recipeEntity)
//        }
//    }
}