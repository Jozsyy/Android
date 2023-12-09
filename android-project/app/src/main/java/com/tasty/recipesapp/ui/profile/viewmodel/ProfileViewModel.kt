package com.tasty.recipesapp.ui.profile.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class ProfileViewModel: ViewModel() { //class ProfileViewModel(private val repository: RecipeRepository): ViewModel() {

    //Live data member
    var myRecipeList: MutableLiveData<List<RecipeModel>> = MutableLiveData()
    var insertResult: MutableLiveData<Boolean> = MutableLiveData()
    var deleteResult: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchRecipeData(context: Context){
        myRecipeList.value = RecipeRepository.getMyRecipe(context)
    }

    fun insertRecipe(recipeModel: RecipeModel){
        RecipeRepository.insertRecipe(recipeModel)
    }

    fun deleteRecipe(recipeModel: RecipeModel){
        RecipeRepository.deleteRecipe(recipeModel)
    }


}