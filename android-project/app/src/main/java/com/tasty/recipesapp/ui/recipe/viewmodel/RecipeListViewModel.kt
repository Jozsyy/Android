package com.tasty.recipesapp.ui.recipe.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.UserRatingsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeListViewModel: ViewModel() {
    private val repository = RecipeRepository

    //Live data member
    var recipeList: MutableLiveData<List<RecipeModel>> = MutableLiveData()

    fun fetchRecipeData(context: Context){
        recipeList.value = repository.getRecipes(context)
    }

//    fun getAllRecipesFromApi(){
//        viewModelScope.launch {
//            val recipes = repository.getRecipesFromApi()
//
//            val recipes = withContext(Dispatchers.IO){
//                repository.getRecipesFromApi("0","50")
//            }
//            recipeList.value = recipes
//        }
//    }
}