package com.tasty.recipesapp.ui.wishlist.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.launch

class WishlistViewModel (private val recipeRepository: RecipeRepository): ViewModel() {

    //Live data member
    var myRecipeList: MutableLiveData<List<RecipeModel>> = MutableLiveData()


    fun fetchRecipeData(context: Context){
        myRecipeList.value = recipeRepository.getMyRecipes(context)
    }

    fun insertRecipe(recipeModel: RecipeModel) {
        recipeRepository.insertRecipe(recipeModel)
    }


    fun deleteRecipe(recipeModel: RecipeModel) {
        recipeRepository.deleteRecipe(recipeModel)
    }
}