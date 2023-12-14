package com.tasty.recipesapp.api

import android.content.ContentValues.TAG
import android.util.Log
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeApiClient {
    companion object {
        private const val BASE_URL = "https://tasty.p.rapidapi.com/"
    }
    private val recipeService: RecipeService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        recipeService = retrofit.create(RecipeService::class.java)
    }

    suspend fun getRecipes(from: String, size: String, tags: String?):
            RecipesDTO? {
        return withContext(Dispatchers.IO) {
            try {
                recipeService.getRecipes(from, size,tags)
            } catch (e: Exception) {
                // Handle exceptions here
                Log.e(TAG, "Error occurred while fetching recipes from API: $e")
                null
            }
        }
    }

    suspend fun getRecipeDetails(id:String): RecipeModel?{
        return withContext(Dispatchers.IO) {
            try {
                recipeService.getRecipeDetails(id)
            }catch (e:Exception){
                Log.e(TAG, "Error occurred while fetching recipe details from API: $e")
                null
            }

        }
    }
}