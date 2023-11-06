package com.tasty.recipesapp.repository.recipe

import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import com.tasty.recipesapp.repository.recipe.model.toModelList
import java.io.IOException

object RecipeRepository {
    private val TAG: String? = RecipeRepository::class.java.canonicalName

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

        return recipesResponse.results.toModelList()
    }
}