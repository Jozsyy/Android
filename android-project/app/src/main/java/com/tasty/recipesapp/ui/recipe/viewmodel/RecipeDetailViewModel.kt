package com.tasty.recipesapp.ui.recipe.viewmodel

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeDatabase
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.toRecipeModel
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val recipeRepository: RecipeRepository) : ViewModel(){//class RecipeDetailViewModel: ViewModel(){

    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()

    fun fetchRecipeDetail(recipeId: Int, context: Context){
        val recipeDatabase = RecipeDatabase.getDatabase(context).recipeDao()
        val recipe = RecipeRepository(recipeDatabase).getRecipe(recipeId)
        this.recipe.value = recipe
    }


    fun fetchRecipeDetailApi(recipeId: Int){
        viewModelScope.launch {
            val recipeDetail = recipeRepository.getRecipeFromApi(recipeId)
            Log.d(TAG,"Recipe detail null: "+recipeDetail.toString())
            if (recipeDetail != null) {
                recipe.value = recipeRepository.getRecipeFromApi(recipeId)
            }
        }
    }

    fun fetchMyRecipeDetail(recipeId: Int, context: Context){
        viewModelScope.launch {
            val recipeDatabase = RecipeDatabase.getDatabase(context).recipeDao()
            val recipeDetail = RecipeRepository(recipeDatabase).getRecipeDB(recipeId)
            if (recipeDetail != null) {
                recipe.value = recipeDetail.toRecipeModel()
            }
        }
    }

}