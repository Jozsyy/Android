package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentNewrecipeBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.UserRatingsModel
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory

class NewRecipeFragment: Fragment() {

    private lateinit var binding:FragmentNewrecipeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNewrecipeBinding.inflate(inflater,container, false)
        return inflater.inflate(R.layout.fragment_newrecipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val factory = ProfileViewModelFactory((activity?.application as App).repository)
        binding.saveNewRecipeButton.setOnClickListener{
            val recipeModel = RecipeModel(
                1,
                binding.titleNewRecipe.toString(),
                binding.descriptionNewRecipe.toString(),
                binding.pictureNewRecipe.toString(),
                UserRatingsModel(score=4F),
                emptyList()
//                binding.userRatingsNewRecipe.toString(),
//                binding.instructionsNewRecipe.toString()
            )
        }
    }
}