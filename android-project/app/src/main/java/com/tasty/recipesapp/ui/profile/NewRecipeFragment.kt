package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentNewrecipeBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.UserRatingsModel
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel

class NewRecipeFragment: Fragment() {

    private lateinit var binding:FragmentNewrecipeBinding
    private lateinit var saveRecipeButton:Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNewrecipeBinding.inflate(inflater,container, false)
        return inflater.inflate(R.layout.fragment_newrecipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val factory = ProfileViewModelFactory((activity?.application as App).repository)

        val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        saveRecipeButton = view.findViewById(R.id.save_newRecipe_button)

        saveRecipeButton.setOnClickListener{
            val recipeModel = RecipeModel(
                1,
                binding.titleNewRecipe.text.toString(),
                binding.descriptionNewRecipe.text.toString(),
                binding.pictureNewRecipe.text.toString(),
                UserRatingsModel(score=4F),
                emptyList()
//                binding.userRatingsNewRecipe.toString(),
//                binding.instructionsNewRecipe.toString()
            )
            viewModel.insertRecipe(recipeModel)
            navigateBack()
        }
    }
    private fun navigateBack(){
        findNavController().popBackStack()
    }
}