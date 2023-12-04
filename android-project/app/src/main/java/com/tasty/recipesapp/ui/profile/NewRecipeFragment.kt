package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory

class NewRecipeFragment: Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val factory = ProfileViewModelFactory((activity?.application as App).repository)
//        binding.saveButton.setOnClickListener{
//            val recipeModel = RecipeModel(
//                1,
//                binding.recipe
//            )
//        }
    }
}