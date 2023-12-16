package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentNewrecipeBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.UserRatingsModel
import com.tasty.recipesapp.repository.recipe.model.toRecipeEntity
import com.tasty.recipesapp.repository.recipe.model.toRecipeEntity_generateID
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory

class NewRecipeFragment: Fragment() {

    private lateinit var binding:FragmentNewrecipeBinding
    //private lateinit var saveRecipeButton:Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNewrecipeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val factory = ProfileViewModelFactory((activity?.application as App).repository)
//        val viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val app = requireActivity().application as App
        val recipeRepository = app.repository

        val viewModelFactory = ProfileViewModelFactory(recipeRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)

//        saveRecipeButton = view.findViewById(R.id.save_newRecipe_button)
//        val title: TextView = view.findViewById(R.id.title_newRecipe)
//        val description: TextView = view.findViewById(R.id.description_newRecipe)
//        val picture:TextView = view.findViewById(R.id.picture_newRecipe)

        binding.saveNewRecipeButton.setOnClickListener{
            val recipeModel = RecipeModel(
                1,
                binding.titleNewRecipe.text.toString(),//title.text.toString(),
                binding.descriptionNewRecipe.text.toString(),//description.text.toString(),
                binding.pictureNewRecipe.text.toString(),//picture.text.toString(),
                UserRatingsModel(0.89F),
                emptyList(),
                binding.videoNewRecipe.text.toString()
//                binding.userRatingsNewRecipe.toString(),
//                binding.instructionsNewRecipe.toString()
            )
            val recipeEntity = recipeModel.toRecipeEntity_generateID()
            viewModel.insertRecipe(recipeEntity)
            navigateBack()
        }
    }
    private fun navigateBack(){
        findNavController().popBackStack()
    }
}