package com.tasty.recipesapp.api

import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: 5743ddb577mshc1af81e86b43109p173dd2jsnb3b4756ef687",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null
    ): RecipesDTO

    @GET("recipes/get-more-info")
    @Headers(
        "X-RapidAPI-Key: 5743ddb577mshc1af81e86b43109p173dd2jsnb3b4756ef687",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipeDetails(
        @Query("id") id: String,
    ): RecipeModel
}