package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipeDetail2Binding
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeDetailViewModel
import com.tasty.recipesapp.ui.recipe.viewmodel.factory.RecipeDetailViewModelFactory

class RecipeDetail2Fragment: Fragment() {

    private lateinit var binding: FragmentRecipeDetail2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        binding = FragmentRecipeDetail2Binding.inflate(inflater, container, false)

        //return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeId: Int? = arguments?.getInt(RecipesFragment.BUNDLE_EXTRA_SELECTED_RECIPE_ID)
//        Log.d(TAG, "Show details for recipe with ID = $recipeId")

        val app = requireActivity().application as App
        val recipeRepository = app.repository

        val viewModelFactory = RecipeDetailViewModelFactory(recipeRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RecipeDetailViewModel::class.java)

        //val factory = RecipeDetailViewModelFactory((activity?.application as App).repository)
        //val viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)


        //Trigger details loading for recipe
        recipeId?.let {
            viewModel.fetchMyRecipeDetail(it, this.requireActivity())
            //viewModel.fetchRecipeDetailApi(it)
        }

        //Subscribe details loading for the recipe
        viewModel.recipe.observe(viewLifecycleOwner) {
//            Log.d(TAG, "Selected recipe's details: $it")
//            Log.d(TAG, "Selected recipe name: ${it.name}")

            binding.recipeItemTitleView.text = it.name
            binding.recipeItemDescriptionView.text = it.description
            binding.recipeItemRatingView.text = (it.user_ratings.score * 10).toString()

            //kep betoltese
            context?.let { it1 ->
                Glide.with(it1)
                    .load(it.thumbnail_url)
                    .centerCrop() //
                    .placeholder(R.drawable.ic_launcher_background) //betolteskor zold kocka meg gondolkozik
                    .fallback(R.drawable.ic_launcher_background) //Ha nem tudja letolteni a kepet
                    .into(binding.recipeImageView)
            }

            if (!it.video_url.isNullOrBlank()){
                binding.recipeVideoView.setVideoPath(it.video_url)
                binding.recipeVideoView.start()
            }
        }
    }
}