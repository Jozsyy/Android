package com.tasty.recipesapp.ui.profile.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.launch
class ProfileViewModel(private val recipeRepository: RecipeRepository): ViewModel() {//class ProfileViewModel: ViewModel() {

    //Live data member
    var myRecipeList: MutableLiveData<List<RecipeModel>> = MutableLiveData()
    var insertResult: MutableLiveData<Boolean> = MutableLiveData()
    var deleteResult: MutableLiveData<Boolean> = MutableLiveData()

//    fun fetchRecipeData(context: Context){
//        myRecipeList.value = recipeRepository.getMyRecipe(context)
//    }
    fun fetchRecipeData(context: Context){
        viewModelScope.launch {
            myRecipeList.value = recipeRepository.getAllRecipes()
        }
    }

//    fun insertRecipe(recipeModel: RecipeModel){
//        recipeRepository.insertRecipe(recipeModel)
//    }
    fun insertRecipe(recipeEntity: RecipeEntity){
        viewModelScope.launch {
            recipeRepository.insertRecipeDB(recipeEntity)
        }
    }

//    fun deleteRecipe(recipeModel: RecipeModel){
//        recipeRepository.deleteRecipe(recipeModel)
//    }

    fun deleteRecipe(recipeEntity: RecipeEntity){
        viewModelScope.launch {
            recipeRepository.deleteRecipeDB(recipeEntity)
        }
    }

    // ViewModel érvénytelenné váltakor a betöltött adatok mentése
//    override fun onCleared() {
//        super.onCleared()
//        // Mentsd el az adatokat, amikor a ViewModel érvénytelenné válik
//        // Az alábbi példa szerint, de a pontos implementáció attól függ, hogyan szeretnéd menteni az adataidat.
//        // Ebben a példában feltételezzük, hogy az adatokat a Repository-ban valamilyen aszinkron módon mented el.
//        myRecipeList.value?.let {
//            for (recipe in it) {
//                saveRecipe(recipe)
//            }
//        }
//    }
//
//    fun saveRecipe(recipeModel: RecipeModel) {
//        // Itt hívhatod meg az adatbázis mentést a RecipeRepository segítségével
//        viewModelScope.launch {
//            try {
//                // Példa: RecipeRepository-n keresztül az adatbázisba való mentés
//                recipeRepository.insertRecipe(recipeModel)
//                // Frissítsd a LiveData-t, hogy az UI azonnal reagáljon a változásra
//                //myRecipeList.value = recipeRepository.getMyRecipe(context)
//                // Állítsd be a sikeres mentés értékét
//                insertResult.value = true
//            } catch (e: Exception) {
//                // Állítsd be a sikertelen mentés értékét, ha valami probléma történik
//                insertResult.value = false
//            }
//        }
//    }

}