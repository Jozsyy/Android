package com.tasty.recipesapp.repository.recipe

import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import com.tasty.recipesapp.repository.recipe.model.toModelList
import java.io.IOException
import java.util.ArrayList

object RecipeRepository {  //private val recipeDao:RecipeDao
    private val TAG: String? = RecipeRepository::class.java.canonicalName
    private var recipeList:List<RecipeModel> = emptyList()
    private var myRecipeList: ArrayList<RecipeModel> = ArrayList()

    fun getRecipes(context: Context): List<RecipeModel>{
        lateinit var jsonString: String

        try{
            jsonString=
                context.assets.open("all_recipes.json")
                    .bufferedReader()
                    .use{
                        it.readText()
                    }
        }catch (ioException: IOException){
            Log.e(TAG,"Error occured while reading JSON file: $ioException")
        }

        //Convert jsonString into Kotlin object
        val recipesResponse: RecipesDTO = Gson().fromJson(jsonString, object : TypeToken<RecipesDTO>() {}.type)

        recipeList = recipesResponse.results.toModelList()
        return recipeList
    }

    fun getRecipe(recipeId: Int): RecipeModel?{
        return recipeList.find{ it.id == recipeId}
    }

    fun insertRecipe(recipeModel: RecipeModel): Boolean{
        return myRecipeList.add(recipeModel)
    }

    fun deleteRecipe(recipeModel: RecipeModel): Boolean{
        return myRecipeList.remove(recipeModel)
    }

    fun getMyRecipe(context: Context) = myRecipeList
}