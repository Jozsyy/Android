package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.util.Log
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentNewrecipeBinding
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeDetailViewModel
import com.tasty.recipesapp.ui.recipe.viewmodel.factory.RecipeDetailViewModelFactory

class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        binding = FragmentRecipeDetailBinding.inflate(inflater,container, false)

        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
        //return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeId: Int? = arguments?.getInt(RecipesFragment.BUNDLE_EXTRA_SELECTED_RECIPE_ID)
        Log.d(TAG, "Show details for recipe with ID = $recipeId")

        //val factory = RecipeDetailViewModelFactory((activity?.application as App).repository)
        val viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)


        //Trigger details loading for recipe
        recipeId?.let{viewModel.fetchRecipeDetail(it, this.requireActivity())}

        //Subscribe details loading for the recipe
        viewModel.recipe.observe(viewLifecycleOwner){
            Log.d(TAG, "Selected recipe's details: $it")
            Log.d(TAG, "Selected recipe name: ${it.name}")


            val title: TextView = view.findViewById(R.id.recipeItemTitleView)
            title.text = it.name
            val description : TextView = view.findViewById(R.id.recipeItemDescriptionView)
            description.text = it.description
            val rating : TextView = view.findViewById(R.id.recipeItemRatingView)
            rating.text = (it.user_ratings.score * 10).toString()
            val picture : ImageView = view.findViewById(R.id.recipeImageView)

            context?.let { it1 ->
                Glide.with(it1)
                    .load(it.thumbnail_url)
                    .centerCrop() //
                    .placeholder(R.drawable.ic_launcher_background) //betolteskor zold kocka meg gondolkozik
                    .fallback(R.drawable.ic_launcher_background) //Ha nem tudja letolteni a kepet
                    .into(picture)
            }

//            binding.recipeItemTitleView.text = it.name
//            binding.recipeItemDescriptionView.text = it.description
//            binding.recipeItemRatingText.text = it.user_ratings.toString()

        }



    }

    private fun updateViews(){

    }
}