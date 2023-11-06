package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.R
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel

class RecipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        context?.let {
//            val recipes = RecipeRepository.getRecipes(it)
//            Log.d(TAG, "Number of recipes= " + recipes.size)
//
//            for(recipe in recipes){
//                Log.d(TAG, "Recipe name: ${recipe.name}")
//            }
//        }
        
        val viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        context?.let{
            viewModel.fetchRecipeData(it)
        }

        viewModel.recipeList.observe(viewLifecycleOwner){recipes ->

            for(recipe: RecipeModel in recipes){
                Log.d(TAG, "Recipe Name: ${recipe.name}")
                Log.d(TAG,"Recipe Description: ${recipe.description}")
                Log.d(TAG, "----------------------------------------")
            }

        }

    }
}