package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.util.Log
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.R
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeDetailViewModel

class RecipeDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeId: Int? = arguments?.getInt(RecipesFragment.BUNDLE_EXTRA_SELECTED_RECIPE_ID)
        Log.d(TAG, "Show details for recipe with ID = $recipeId")

        val viewModel = ViewModelProvider(this)[RecipeDetailViewModel::class.java]

        //Trigger details loading for recipe
        recipeId?.let{viewModel.fetchRecipeDetail(it, this.requireActivity())}

        //Subscribe details loading for the recipe
        viewModel.recipe.observe(viewLifecycleOwner){
            Log.d(TAG, "Selected recipe's details: $it")
        }
    }

    private fun updateViews(){

    }
}