package com.tasty.recipesapp.repository.recipe

import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.tasty.recipesapp.api.RecipeApiClient
import com.tasty.recipesapp.repository.recipe.model.*
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList

class RecipeRepository(private val recipeDao:RecipeDao) {  //object -()
    private val TAG: String? = RecipeRepository::class.java.canonicalName
    private var recipeList: List<RecipeModel> = emptyList()
    private var myRecipeList: MutableList<RecipeModel> = emptyList<RecipeModel>().toMutableList()
    private var wishlistRecipeList: MutableList<RecipeModel> = emptyList<RecipeModel>().toMutableList()


    //API client
    private val recipeApiClient = RecipeApiClient()

    suspend fun getRecipesFromApi(from: String, size: String, tags: String?): List<RecipeModel> {
        val recipesDTO: RecipesDTO? = recipeApiClient.getRecipes(from, size, tags)
        if (recipesDTO != null) {
            //Log.d(TAG,"Recipes: "+recipesDTO.toString())
            return recipesDTO.results.toModelList()
        } else {
            Log.d(TAG, "Error getting recipes: " + recipesDTO.toString())
            return emptyList()
        }
    }


    fun getRecipes(context: Context): List<RecipeModel> {
        lateinit var jsonString: String

        try {
            jsonString =
                context.assets.open("all_recipes.json")
                    .bufferedReader()
                    .use {
                        it.readText()
                    }
        } catch (ioException: IOException) {
            Log.e(TAG, "Error occured while reading JSON file: $ioException")
        }

        //Convert jsonString into Kotlin object
        val recipesResponse: RecipesDTO = Gson().fromJson(jsonString, object : TypeToken<RecipesDTO>() {}.type)

        recipeList = recipesResponse.results.toModelList()
        return recipeList
    }

    fun getRecipe(recipeId: Int): RecipeModel? {
        return recipeList.find { it.id == recipeId }
    }

    suspend fun getRecipeFromApi(recipeId: Int): RecipeModel? {
        return recipeApiClient.getRecipeDetails(recipeId.toString())
    }

    fun insertRecipe(recipeModel: RecipeModel): Boolean {
        return myRecipeList.add(recipeModel)
    }

    fun deleteRecipe(recipeModel: RecipeModel): Boolean {
        return myRecipeList.remove(recipeModel)
    }

    fun getMyRecipes(context: Context) = myRecipeList

    suspend fun insertRecipeDB(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun getRecipeDB(recipeId: Int): RecipeEntity? {
        return recipeDao.getRecipeById(recipeId.toLong()) //find { it.id == recipeId }
    }

    suspend fun getAllRecipes(): List<RecipeModel> {
        return recipeDao.getAllRecipes().map {
            val jsonObject = JSONObject(it.json)
            jsonObject.apply { put("id", it.internalId) }
            Gson().fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
        }
    }

    suspend fun deleteRecipeDB(recipe: RecipeEntity){
        recipeDao.deleteRecipe(recipe)
    }
}